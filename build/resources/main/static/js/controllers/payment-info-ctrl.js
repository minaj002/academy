/**
 * Alerts Controller
 */

angular
    .module('RDash')
    .controller('PaymentCtrl', ['$scope', '$http', '$filter', '$state','Base64', 'moment', PaymentCtrl]);

function PaymentCtrl($scope, $http, $filter, $state, Base64, moment) {
    if(!$scope.credentials.valid){
        alert($scope.credentials.user);
        $state.transitionTo('login');
    }
//    $scope.member = {};
    $scope.payments = {};
    $scope.newPayment = {};

    $scope.showPayments = function(member) {
        var x = $scope.credentials;
        var date = moment().add(1, 'day').format('YYYY-MM-DD');
        setHeaders(x.user, x.password, $http, Base64);
        var query = "payments/"+date+"/12/"+member.memberId;
        $scope.member = member;
        $http
             .get(query)
             .then(function(response) {
             $scope.payments = response.data;
             if($scope.payments.length == 0){
                $scope.newPayment.memberId = member.memberId;
                $scope.newPayment.amount = 45;
                var today = moment().format('YYYY-MM-DD');
                $scope.newPayment.paymentDate = today;
                var paidUntilDate = moment().add(1, 'month').format('YYYY-MM-DD');
                $scope.newPayment.paidUntil = paidUntilDate;

             } else {
                $scope.newPayment.memberId = $scope.payments[0].memberId;
                $scope.newPayment.amount = $scope.payments[0].amount;
                var today = moment().format('YYYY-MM-DD');
                $scope.newPayment.paymentDate = today;
                var paidUntilDate = moment($scope.payments[0].paidUntil, 'YYYY-MM-DD').add(1, 'month').format('YYYY-MM-DD');
                $scope.newPayment.paidUntil = paidUntilDate;
             }
             });
    };

    $scope.newPayment = {}

    $scope.addPayment = function() {
        var x = $scope.credentials;
        setHeaders(x.user, x.password, $http, Base64);
        $http.post("/payments/new", $scope.newPayment)
                 .then(function(response) {
                    $scope.payment.id = response;
                    $scope.payments.push($scope.payment);
                 });
    }

}
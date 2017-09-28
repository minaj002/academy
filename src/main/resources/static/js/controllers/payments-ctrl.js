/**
 * Alerts Controller
 */

angular
    .module('RDash')
    .controller('AllPaymentsCtrl', ['$scope', '$http', '$filter', '$state', 'Base64', 'moment', AllPaymentsCtrl]);

function AllPaymentsCtrl($scope, $http, $filter, $state, Base64, moment) {
    if(!angular.isDefined($scope.credentials)||!$scope.credentials.valid){
        $state.transitionTo('login');
    }
    $scope.payments = {};
    $scope.paymentMembers = [];
    $scope.paymentsFrom = moment().format('YYYY-MM-DD');
    $scope.showPayments = function() {
        var x = $scope.credentials;
        setHeaders(x.user, x.password, $http, Base64);
        var query = "payments/" + $scope.paymentsFrom;
        $http
             .get(query)
             .then(function(response) {
                 $scope.payments = response.data;
                 angular.forEach($scope.payments, function(payment, key) {
                    var member = $scope.matchMember(payment.memberId);
                    $scope.paymentMembers.push({mem : member, pay : payment});
                 });
             });


    };

    $scope.matchMember = function(memId) {
         var found = $filter('filter')($scope.members, {memberId: memId}, true);
         if (found.length) {
             return found[0];
         }
     }

}
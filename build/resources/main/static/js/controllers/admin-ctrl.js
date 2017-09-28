/**
 * Alerts Controller
 */

angular
    .module('RDash')
    .controller('AdminCtrl', ['$scope', '$http', '$state', 'Base64', AdminCtrl]);

function AdminCtrl($scope, $http, $state, Base64) {
    if(!angular.isDefined($scope.credentials)||!$scope.credentials.valid){
        $state.transitionTo('login');
    }

    $scope.newMember = {};
    $scope.newMember.joinDate = new Date();
    $scope.addMember = function() {
        $scope.alerts.push({
            msg: 'Another alert!'
        });
    };

    $scope.submitNewMember = function() {
        var x = $scope.credentials;

        setHeaders(x.user, x.password, $http, Base64);
        $scope.newMember.joinDate = new Date();
            $http.post("members/new", $scope.newMember)
                 .then(function(response) {
                     alert("New member " + $scope.newMember.firstName + " added");
                     $scope.newMember = {};
                 });
    };

    $scope.newMember = {};
}
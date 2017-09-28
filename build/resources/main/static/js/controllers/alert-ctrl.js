/**
 * Alerts Controller
 */

angular
    .module('RDash')
    .controller('AlertsCtrl', ['$scope', AlertsCtrl]);

function AlertsCtrl($scope) {
    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };
}
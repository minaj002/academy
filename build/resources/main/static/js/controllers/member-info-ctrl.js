
angular
    .module('RDash')
    .controller('MemberCtrl', ['$scope', '$http', '$state','Base64', MemberCtrl]);

function MemberCtrl($scope, $http, $state, Base64) {
    if(!$scope.credentials.valid){
        $state.transitionTo('login');
    }

    $scope.member = {};

    $scope.showMember = function(member) {
        $scope.member = member;
    };

    $scope.editMember = function() {
        var x = $scope.credentials;

        setHeaders(x.user, x.password, $http, Base64);
            $http.post("members/edit", $scope.member)
                 .then(function(response) {
                     alert("Member " + $scope.member.firstName + " edited")
                 });
    };

}
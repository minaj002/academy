/**
 * Master Controller
 */

angular.module('RDash')
    .controller('MasterCtrl', ['$scope', '$cookieStore', '$filter', '$state', '$http', '$location', 'Base64', MasterCtrl])

        .factory('Base64', function () {
            var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';
            return {
                encode: function (input) {
                    var output = "";
                    var chr1, chr2, chr3 = "";
                    var enc1, enc2, enc3, enc4 = "";
                    var i = 0;

                    do {
                        chr1 = input.charCodeAt(i++);
                        chr2 = input.charCodeAt(i++);
                        chr3 = input.charCodeAt(i++);

                        enc1 = chr1 >> 2;
                        enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                        enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                        enc4 = chr3 & 63;

                        if (isNaN(chr2)) {
                            enc3 = enc4 = 64;
                        } else if (isNaN(chr3)) {
                            enc4 = 64;
                        }

                        output = output +
                            keyStr.charAt(enc1) +
                            keyStr.charAt(enc2) +
                            keyStr.charAt(enc3) +
                            keyStr.charAt(enc4);
                        chr1 = chr2 = chr3 = "";
                        enc1 = enc2 = enc3 = enc4 = "";
                    } while (i < input.length);

                    return output;
                },

                decode: function (input) {
                    var output = "";
                    var chr1, chr2, chr3 = "";
                    var enc1, enc2, enc3, enc4 = "";
                    var i = 0;

                    // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
                    var base64test = /[^A-Za-z0-9\+\/\=]/g;
                    if (base64test.exec(input)) {
                        window.alert("There were invalid base64 characters in the input text.\n" +
                            "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                            "Expect errors in decoding.");
                    }
                    input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

                    do {
                        enc1 = keyStr.indexOf(input.charAt(i++));
                        enc2 = keyStr.indexOf(input.charAt(i++));
                        enc3 = keyStr.indexOf(input.charAt(i++));
                        enc4 = keyStr.indexOf(input.charAt(i++));

                        chr1 = (enc1 << 2) | (enc2 >> 4);
                        chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                        chr3 = ((enc3 & 3) << 6) | enc4;

                        output = output + String.fromCharCode(chr1);

                        if (enc3 != 64) {
                            output = output + String.fromCharCode(chr2);
                        }
                        if (enc4 != 64) {
                            output = output + String.fromCharCode(chr3);
                        }

                        chr1 = chr2 = chr3 = "";
                        enc1 = enc2 = enc3 = enc4 = "";

                    } while (i < input.length);

                    return output;
                }
            };
        });




function MasterCtrl($scope, $cookieStore, $filter, $state, $http, $location, Base64) {
    /**
     * Sidebar Toggle & Cookie Control
     */
    var mobileView = 992;

    $scope.getWidth = function() {
        return window.innerWidth;
    };

    $scope.$watch($scope.getWidth, function(newValue, oldValue) {
        if (newValue >= mobileView) {
            if (angular.isDefined($cookieStore.get('toggle'))) {
                $scope.toggle = ! $cookieStore.get('toggle') ? false : true;
            } else {
                $scope.toggle = true;
            }
        } else {
            $scope.toggle = false;
        }

    });

    if(!angular.isDefined($scope.credentials)){
        $state.transitionTo('login');
    }

    $scope.toggleSidebar = function() {
        $scope.toggle = !$scope.toggle;
        $cookieStore.put('toggle', $scope.toggle);
    };

    window.onresize = function() {
        $scope.$apply();
    };

    $scope.credentials = {
        valid: false, user: 'admin@bjj.com', password: 'password'
    };

    $scope.login = function() {
        var x = $scope.credentials;  
        x.valid = true;
        getMembers(x.user, x.password, $http, $scope, Base64);
        $location.path("/check-in");
    };

    $scope.checkin = function(member){
        var index = $scope.members.indexOf(member);
        $scope.members.splice(index, 1);
        $scope.checkedMembers.push(member);
    };

    $scope.submitMembers = function(){
                 classAttended = {'members' : $scope.checkedMembers, 'date' : new Date(), 'id': null};
                 $http.defaults.headers.common.Authorization = 'Basic ' + Base64.encode('admin@bjj.com' + ':' + 'password');
                 $http
                      .post("/classes/new", classAttended)
                      .then(function(response) {
                          getMembers('admin@bjj.com', 'password', $http, $scope, Base64);
                          $scope.checkedMembers = [];
                      });

    };

    $scope.members = [];
    $scope.checkedMembers = [];
    $scope.sortType     = ['lastName','firstName' ]; // set the default sort type
    $scope.sortReverse  = false;  // set the default sort order
    $scope.findUser   = '';     // set the default search/filter term

}

    function getMembers(user, password, $http, $scope, Base64){
            setHeaders(user, password, $http, Base64)
            $http
                 .get("members")
                 .then(function(response) {
                     $scope.members = response.data;
                 });
    }

    function setHeaders(user, password, $http, Base64){
                    $http.defaults.headers.common.Authorization = 'Basic ' + Base64.encode(user + ':' + password);

    }
'use strict';

/**
 * Route configuration for the RDash module.
 */
angular.module('RDash').config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {

        // For unmatched routes
        $urlRouterProvider.otherwise('/');

        // Application routes
        $stateProvider
            .state('login', {
                url: '/',
                templateUrl: 'templates/login.html'
            })
            .state('check-in', {
                url: '/check-in',
                templateUrl: 'templates/check-in.html'
            })
            .state('new', {
                url: '/new-member',
                templateUrl: 'templates/new-member.html'
            })
            .state('info', {
                url: '/members-info',
                templateUrl: 'templates/members-info.html'
            })
            .state('payment', {
                url: '/payment-info',
                templateUrl: 'templates/payment-info.html'
            })
            .state('payments', {
                url: '/payments',
                templateUrl: 'templates/payments.html'
            });
    }
]);
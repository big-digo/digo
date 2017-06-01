'use strict';
   /* angular.module('app')*/
app.config(['$urlRouterProvider',function($urlRouterProvider){
    $urlRouterProvider
        .when('/', ['$state', function ($state) {
            $state.go('app.home');
        }]).when('', ['$state', function ($state) {
            $state.go('app.home');
        }]);
    $urlRouterProvider.otherwise("/404");
}]);

        app.config(['$routeProvider', '$stateProvider', '$urlRouterProvider','$controllerProvider',
            '$compileProvider', '$filterProvider', '$provide',
            '$locationProvider', '$httpProvider',function($routeProvider,$stateProvider,$urlRouterProvider,$controllerProvider,
                                                          $compileProvider, $filterProvider, $provide,
                                                          $locationProvider, $httpProvider) {
                app.controller = $controllerProvider.register;
                app.directive = $compileProvider.directive;
                app.filter = $filterProvider.register;
                app.factory = $provide.factory;
                app.service = $provide.service;
                app.constant = $provide.constant;
                app.value = $provide.value;

                if (!$httpProvider.defaults.headers.get) {
                    $httpProvider.defaults.headers.get = {};
                }

                // Disable IE ajax request caching
                $httpProvider.defaults.headers.get['Cache-Control'] = 'no-cache';
                $httpProvider.defaults.headers.get['Pragma'] = 'no-cache';

            $stateProvider
                .state('app', {
                    url: "/app",
                    abstract: true,
                    templateUrl: "template/home/home.html"
                })
                .state('app.home',{
                    url:'/home',
                    templateUrl: "template/dashboard/dashboard.html",
                    controller: 'dashboardCtrl'
                })
                .state('app.data_link',{
                    url:'/data_link',
                    templateUrl: "template/datasource/data_link.html",
                    controller: 'dataLinkCtrl'
                })
                .state('app.data_model',{
                    url:'/data_model',
                    templateUrl: "template/datasource/data_model.html",
                    controller: 'dataModelCtrl'
                })
                .state('app.data_policy',{
                    url:'/data_policy',
                    templateUrl: "template/datasource/data_policy.html",
                    controller: 'dataModelCtrl'
                })
                .state('app.report', {
                    url: '/report',
                    templateUrl: 'template/report/report.html',
                    controller: 'reportCtrl'
                })
        }]
    );

  app.run(
        ['$rootScope', '$state', '$stateParams',
            function ($rootScope, $state, $stateParams) {
                $rootScope.$state = $state;
                $rootScope.$stateParams = $stateParams;
            }
        ]
    );


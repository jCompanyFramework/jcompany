(function() {
  'use strict';

  angular
    .module('rhdemo')
    .config(RouterConfig);

    /** @ngInject */
  function RouterConfig($stateProvider, $urlRouterProvider) {
    
    $stateProvider
      .state('inicial', {
        url: '/',
        templateUrl: 'app/inicial/inicial.html',
        controller: 'InicialController',
        controllerAs: 'inicial'
      });

    $stateProvider
      .state('tables', {
        url: '/tables',
        templateUrl: 'templates/tables.html',
        controller: '',
        controllerAs: ''
      });

    $stateProvider
      .state('dashboard', {
        url: '/dashboard',
        templateUrl: 'templates/dashboard.html',
        controller: '',
        controllerAs: ''
      });

    $stateProvider
      .state('departamentosel', {
        url: '/departamentosel',
        templateUrl: 'app/departamento/departamentosel.html',
        controller: 'DepartamentoController',
        controllerAs: 'departamentoController'
      });

    $stateProvider
      .state('departamentoman', {
        url: '/departamentoman',
        templateUrl: 'app/departamento/departamentoman.html',
        controller: 'DepartamentoController',
        controllerAs: 'departamentoController'
      });

    $stateProvider
      .state('uf', {
        url: '/uf',
        templateUrl: 'app/uf/uf.html',
        controller: 'ufController',
        controllerAs: 'ufController'
      });

    $stateProvider
      .state('funcionariosel', {
        url: '/funcionariosel',
        templateUrl: 'app/funcionario/funcionariosel.html',
        controller: 'funcionarioController',
        controllerAs: 'funcionarioController'
      });

    $stateProvider
      .state('funcionariomdt', {
        url: '/funcionariomdt',
        templateUrl: 'app/funcionario/funcionariomdt.html',
        controller: 'funcionarioController',
        controllerAs: 'funcionarioController'
      });

    $urlRouterProvider.otherwise('/');
  }

})();



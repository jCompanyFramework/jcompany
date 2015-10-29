
(function() {
  'use strict';

  angular
    .module('rhdemo')
    .controller('DepartamentoController', DepartamentoController );

  DepartamentoController.$inject = ['$rootScope', '$scope', '$state', 'DepartamentoService', 'notificationService'];

  /** @ngInject */
  function DepartamentoController($rootScope, $scope, $state, DepartamentoService, notificationService) {

    $scope.find = function(){
      DepartamentoService._all($scope.departamentoArg).then( function (response) {
        if (response.data.length == 0){
          notificationService.info("NENHUM_REGISTRO_ENCONTRADO_022");
        }
        $scope.gridOptions.data = response.data;
      });
    };

    $scope.clear = function(){
      $scope.departamentoArg = new Object();
      $scope.gridOptions.data = [];
    };

    $scope.edit = function(row){
      DepartamentoService.edit(row.entity.id).then( function (response) {
        $state.go( 'departamentoman' );
        $rootScope.departamento = response.data;
      });
    };

    $scope.save = function(){
      DepartamentoService.save($scope.departamento).then( function (response) {
          $rootScope.departamento = response.data;
          notificationService.success("DADOS_SALVOS_SUCESSO_000");
      });
    };

    $scope.remove = function(){
      DepartamentoService.remove($scope.departamento).then( function (response) {
          $rootScope.departamento = response.data;
          notificationService.success("REGISTRO_EXCLUIDO_SUCESSO_021");
      });
    };

    $scope.new = function () {
      $rootScope.departamento = new Object();
      $state.go( 'departamentoman' );
    };

    $scope.list = function () {
      $scope.departamentoArg = new Object();
      $state.go( 'departamentosel' );
    };

    
    function rowTemplate() {
      return '<div ng-click="grid.appScope.edit(row)" >' +
             '  <div ng-repeat="(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name" class="ui-grid-cell" ng-class="{ \'ui-grid-row-header-cell\': col.isRowHeader }"  ui-grid-cell></div>' +
             '</div>';
    }

    $scope.gridOptions = {
      paginationPageSizes: [25, 50, 75],
      paginationPageSize: 25,
      rowTemplate: rowTemplate(),
      columnDefs: [
        { field: 'id', displayName: 'Id', width: '10%'},
        { field: 'descricao', displayName: 'Descrição', width: '90%'}
      ]
    };

  }

})();



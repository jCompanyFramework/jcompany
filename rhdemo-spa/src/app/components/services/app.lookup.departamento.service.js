/**=========================================================
 * Provides a static lookup from a EstadoCivil Enum
 * @leandro.lima
 =========================================================*/
(function() {
  'use strict';

  angular
    .module('rhdemo')
    .factory('appLookupDepartamentoService', AppLookupDepartamentoService);

  AppLookupDepartamentoService.$inject = ['EntityService'];

  /** @ngInject */
  function AppLookupDepartamentoService(EntityService) {
  	 
  	var service = $class(EntityService, {

        constructor: function() {
            EntityService.call(this, {
                 type: 'departamento'
            });
        },

        /* METODOS REST */
        _all: function() {
            return this._get('/all');
        },
       
    });


    return new service();
  }
  
})();

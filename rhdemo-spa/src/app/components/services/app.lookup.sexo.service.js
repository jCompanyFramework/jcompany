/**=========================================================
 * Provides a static lookup from a Sexo Enum
 * @leandro.lima
 =========================================================*/
(function() {
  'use strict';

  angular
    .module('rhdemo')
    .factory('appLookupSexoService', AppLookupSexoService);

  AppLookupSexoService.$inject = ['EntityService'];

  /** @ngInject */
  function AppLookupSexoService(EntityService) {
  	 
  	var service = $class(EntityService, {

        constructor: function() {
            EntityService.call(this, {
                 type: 'lookup/sexo'
            });
        },

        /* METODOS REST */
        _all: function() {
            return this._get('/');
        },
       
    });


    return new service();
  }
  
})();

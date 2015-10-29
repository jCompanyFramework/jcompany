/**=========================================================
 * Provides a static lookup from a EstadoCivil Enum
 * @leandro.lima
 =========================================================*/
(function() {
  'use strict';

  angular
    .module('rhdemo')
    .factory('appLookupEstadoCivilService', AppLookupEstadoCivilService);

  AppLookupEstadoCivilService.$inject = ['EntityService'];

  /** @ngInject */
  function AppLookupEstadoCivilService(EntityService) {
  	 
  	var service = $class(EntityService, {

        constructor: function() {
            EntityService.call(this, {
                 type: 'lookup/estadocivil'
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

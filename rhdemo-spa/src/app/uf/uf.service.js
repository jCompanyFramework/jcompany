(function() {
  'use strict';

  angular
    .module('rhdemo')
    .factory('ufService', UfService);

  UfService.$inject = ['EntityService'];

  /** @ngInject */
  function UfService(EntityService) {

  	 
  	var Service = $class(EntityService, {

        constructor: function() {
            EntityService.call(this, {
                 type: 'unidadefederativa'
            });
        },

        /* METODOS REST */
        _all: function() {
            return this._get('/all');
        },

        create: function() {
            return this._get('/create');
        },

        edit: function(id) {
            return this._get('/get/' + id);
        },

        save: function(data) {
            return this._post('/save', data);
        },

        remove: function(data) {
            return this._post('/remove', data);
        }
       
    });


    return new Service();
  }
  
})();

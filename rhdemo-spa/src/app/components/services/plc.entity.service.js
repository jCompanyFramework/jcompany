(function() {
  'use strict';

    angular.module('rhdemo')
        .factory('EntityService', EntityService); 
    
    EntityService.$inject = ['RestService'];


    function EntityService(RestService) {

        var EntityService = $class({

            constructor: function(config) {

                this.type = config.type || '';

                this.rest = new RestService({
                    path: '/rest/entity'
                });
            },

            // from DeferService
            isWorking: function() {
                return this.rest.isWorking();
            },

            _entityPath: function(actionPath) {
                return '/' + this.type + (actionPath || '');
            },

            _get: function(uri, config) {
                return this.rest.get(this._entityPath(uri), config);
            },

            _post: function(uri, data, config) {
                return this.rest.post(this._entityPath(uri), data, config);
            },

            find: function(filter, pagination) {

                pagination = pagination || {};

                var path = this.rest.toMatrixPath(this._entityPath('/find'), filter);

                var config = {
                    params: {
                        page: pagination.page || 0,
                        size: pagination.size || 20,
                        dir: pagination.dir || 'ASC', // ASC|DESC
                        order: pagination.order || ''
                    }
                };

                return this.rest.get(path, config);
            },

            create: function() {
                return this._get('/create');
            },

            get: function(id, config) {
                return this._get('/get/' + id);
            },

            save: function(data) {
                return this._post('/save', data);
            },

            remove: function(data) {
                return this._post('/remove', data);
            }
        });

        return EntityService;
    };


})();

angular.module('rhdemo')
    .factory('DeferService', ['$q', function DeferServiceFactory($q) {
    'use strict';

    return $class({

        constructor: function() {
            this._deferQueue = 0;
            this._deferFinally = angular.bind(this, this._deferEnd);
        },

        _deferStart: function(def) {

            var p = def.promise || def;

            if (p.finally) {
                this._deferQueue++;
                // console.log('Will deferSTart ' + this._deferQueue, this);
                p.finally(this._deferFinally);
            }
            return def;
        },

        _deferEnd: function() {
            // console.log('Will deferEnd ' + this._deferQueue, this);
            this._deferQueue--;
        },

        defer: function(d) {
            return this._deferStart(d || $q.defer());
        },

        isWorking: function() {
            return this._deferQueue > 0;
        }
    });
}]);

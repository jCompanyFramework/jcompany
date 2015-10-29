/* global malarkey:false, moment:false */
(function() {
  'use strict';

  angular
    .module('rhdemo')
    .constant('moment', moment)
    .constant('$contextUrl', '/')
    .constant('$backendUrl', 'http://localhost:7001/rhdemo-service');

})();

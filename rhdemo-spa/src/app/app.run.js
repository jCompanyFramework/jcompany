(function() {
  'use strict';

  angular
    .module('rhdemo')
    .run(runBlock);

  /** @ngInject */
  function runBlock($log, authService) {

    $log.debug('runBlock end');
    
    authService.load();
    
  }

})();

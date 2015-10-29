(function() {
  'use strict';


angular
    .module('rhdemo')
    .controller('AlertsController', ['$scope', AlertsController] );

function AlertsController($scope) {

    $scope.alerts = [{
        type: 'success',
        msg: 'Mensagem de SUCESSO exemplo RhDemo, para verificar abra o alert.controller.js'
    }, {
        type: 'danger',
        msg: 'Mensagem de ERRO exemplo RhDemo, para verificar abra o alert.controller.js'
    }];

    $scope.addAlert = function() {
        $scope.alerts.push({
            msg: 'Another alert!'
        });
    };

    $scope.closeAlert = function(index) {
        $scope.alerts.splice(index, 1);
    };
}

})();
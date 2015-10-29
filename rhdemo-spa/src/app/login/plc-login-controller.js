angular
.module('rhdemo')
.controller('LoginController', ['$contextUrl', '$scope', '$window', 'authService', 'notificationService', 

function LoginController($contextUrl, $scope, $window, authService, notificationService) {
	'use strict';

	$scope.username = authService.user.username;
	$scope.password = '';

	$scope.isLoading = function(){
		return authService.isWorking();
	};

	$scope.login = function($event) {
		$event.preventDefault();

		if(!$scope.username || !$scope.password){
			notificationService.error("FALHA_LOGIN_001");
			return;
		}

		if (authService.isLogged()) {
			$window.location.href = '/';
		} else {
			authService
			.login($scope.username, $scope.password)
			.then(function(userInfo) {
				$window.location.href = '/';
			}, function(result) {
				notificationService.push(result);
			});
		}
	};

}]);

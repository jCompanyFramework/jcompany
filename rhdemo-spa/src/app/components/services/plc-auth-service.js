angular
    .module('rhdemo')
    .factory('authService', ['$rootScope', '$window', 'DeferService', 'RestService', '$contextUrl',
    function authServiceFactory($rootScope, $window, DeferService, RestService, $contextUrl) {
    'use strict';

    var AuthService = $class(DeferService, {

        constructor: function() {

            DeferService.call(this);

            this.user = false;
            this.loaded = false;

            this.rest = new RestService({
                path: '/rest/auth'
            });

            this.sessionTimer = 0;
            this.sessionTimeout = 3600;
        },

        _httpBind: function(http, ok, error) {
            // http already have deferWork from DeferService
            return http.then(angular.bind(this, ok), angular.bind(this, error));
        },

        check: function() {

            var deferred = this.defer();

            this._httpBind(

                this.rest.get('/user'),

                function(response) {
                    var user = response && response.data;
                    if (user) {
                        // copy internal
                        this.user = angular.copy(user, {});
                        $rootScope.user = this.user;
                        // ok
                        deferred.resolve(user);
                    } else {
                        deferred.reject(this.user = false);
                    }
                },

                function(error) {
                    deferred.reject(this.user = false);
                }
            );
            return deferred.promise;
        },

        _checkSession: function() {

            var deferred = this.defer();

            this._httpBind(

                this.rest.get('/checkSession'),

                function(response) {
                    var user = response && response.data;
                    if (user) {
                        // copy internal
                        this.user = angular.copy(user, {});
                        // ok
                        deferred.resolve(user);
                    } else {
                        deferred.reject(this.user = false);
                    }
                },
                function() {
                    deferred.reject(this.user = false);
                }
            );
            return deferred.promise;
        },

        checarSessao: function() {
            var auth = this;
            var loginPage = 'plc-login.html';
            var currentPage = $window.location.toString();

            auth._checkSession()
                .then(function(userInfo) {
                    // login ok
                    if (!userInfo) {
                        $window.location.href = loginPage;
                    }
                }, function() {
                    // not logged
                    if (currentPage.indexOf(loginPage) === -1) {
                        $window.location.href = loginPage;
                    }
                }).finally(function() {
                    auth.loaded = true;
                });
        },

        login: function(username, password) {

            var deferred = this.defer();

            this._httpBind(

                this.rest.post('/login', {
                    username: username,
                    password: password
                }),

                function(response) {
                    var user = response && response.data;
                    if (user) {
                        // copy internal
                        this.user = angular.copy(user, {});
                        $rootScope.user = this.user;
                        // ok
                        deferred.resolve(user);
                    } else {
                        deferred.reject(this.user = false);
                    }
                },

                function() {
                    deferred.reject(this.user = false);
                }
            );

            return deferred.promise;
        },

        logout: function() {

            var deferred = this.defer();

            this._httpBind(

                this.rest.post('/logout'),

                function(ok) {
                    if (ok) {
                        this.user = false;
                        $rootScope.user = null;
                        deferred.resolve(ok);
                    } else {
                        deferred.reject();
                    }
                },

                function() {
                    deferred.reject();
                }
            );

            return deferred.promise;
        },

        load: function() {
            if (this.loaded) {
                return;
            }

            var auth = this;
            var loginPage = 'plc-login.html';
            var currentPage = $window.location.toString();

            if (currentPage.indexOf('/index.html') === -1) {
                // execute auth check and fire events LOGIN
                auth.check()
                    .then(function(userInfo) {
                        // login ok
                
                    }, function() {
                        // not logged
                        if (currentPage.indexOf(loginPage) === -1) {
                            $window.location.href = loginPage;
                        }
                    }).finally(function() {
                        auth.loaded = true;
                    });
            }
            // Cada troca de view faz checagem de permissao.
            $rootScope.$on('$routeChangeStart', function(event, nextRoute, currRoute) {

                auth.checarSessao();

                var access = (nextRoute && nextRoute.access) || 'private';

                if (!auth.hasRole(access)) {
                    if (!auth.isLogged()) {
                        // $window.location.href = 'login.html';
                        // console.log('user not authenticated');
                    } else {
                        // $location.path('/403');
                        // console.log('user has no access role');
                    }
                }
            });
        },


        isLogged: function() {
            // console.log(this.grupoTrabalho);
            // if(this.grupoTrabalho !== false){
                return (this.user !== false);
            // }
            // return null;
        },


        getPerfil : function() {
            var perfil = null;
            if (this.user) {
                var permissoes = this.user.usuario.permissoes;
                if (this.isMagistrate()) {
                    perfil = "Magistrado";
                } else if (permissoes == "A") {
                    perfil = "Perfil Administrador";
                } else if (permissoes == "W") {
                    perfil = "Perfil Leitura e Escrita";
                } else {
                    perfil = "Perfil Somente Leitura";
                }
            }
            return perfil;
        },

        getUsername: function() {
            return this.user ? this.user.login : null;
        },

        getName: function() {
            return this.user ? this.user.name : null;
        },

        getUser: function() {
            if (this.user) {
                return this.user;
            }
            return null;
        },


        hasRole : function(role) {
            if (role === 'public' || (role === 'private' && this.user)) {
                return true;
            } else if (this.user && this.user.roles && this.user.roles.length > 0) {
                return this.user.roles.indexOf(role) != -1;
            }
            return false;
        }

    });

    return new AuthService();
}]);

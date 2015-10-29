angular.module('rhdemo')
    .factory('messageService', ['$q', '$http', '$backendUrl', 'DeferService', function mensagensServiceFactory($q, $http, $backendUrl, DeferService) {
    'use strict';

    var MessageService = $class(DeferService, {

        constructor: function() {
            this.load();
        },

        _onLoad: function(response){
            this.messages = response.data.messages;
        },

        load: function() {
            this.defer($http.get($backendUrl + '/rest/mensagens'))
                .then(angular.bind(this, this._onLoad));
        },

        getMessage: function(msg, type, args) {

            var messageKey = msg.key || msg;
            var messageType = type || msg.type || 'INFO';

            var defaultMessages = this.messages.INFO;
            var specificMessages = this.messages[messageType];

            var keyComparator = function(m) {
                return m.key.name === messageKey;
            };

            var messageText = specificMessages && _.find(specificMessages, keyComparator);

            if (!messageText) {
                messageText = defaultMessages && _.find(defaultMessages, keyComparator);
                if(args && Array.isArray(args)) {
                    var customMessage = angular.copy(messageText, {});
                    if (customMessage && customMessage.message) {
                        args.forEach(function (arg, i) {
                            customMessage.message = customMessage.message.replace("{"+i+"}", arg);
                        });
                        return customMessage;
                    }
                }
            }

            return messageText || messageKey;
        }
    });

    return new MessageService();
}]);

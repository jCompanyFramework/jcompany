angular.module('rhdemo')
    .factory('notificationService', ['Notification', 'messageService', 'DeferService', function notificationServiceFactory(Notification, mensagensService, DeferService) {
    'use strict';

    var types = {
        SUCCESS: "SUCCESS",
        WARNING: "WARNING",
        ERROR: "ERROR",
        INFO: "INFO"
    };

    var NotificacaoService = $class(DeferService, {

        push: function(content) {
            if (content) {
                if (content.SUCCESS) {
                    this.pushMessages(content.SUCCESS);
                }
                if (content.INFO) {
                    this.pushMessages(content.INFO);
                }
                if (content.WARNING) {
                    this.pushMessages(content.WARNING);
                }
                if (content.ERROR) {
                    this.pushMessages(content.ERROR);
                }
            }
        },

        pushMessages: function(messages) {
            for (var i = 0; i < messages.length; i++) {
                this.pushMessage(messages[i]);
            }
        },

        pushMessage: function(message, type) {

            type = (type || message.type || types.INFO).toUpperCase();

            if (type === types.ERROR) {
                this.error(message, message.args);
            } else if (type === types.WARNING) {
                this.warning(message, message.args);
            } else if (type === types.SUCCESS) {
                this.success(message, message.args);
            } else {
                this.info(message, message.args);
            }
        },

        translateMessage: function(msg, type, args) {
            return mensagensService.getMessage(msg, type, args);
        },
        error: function(msg, args) {
            Notification.error(this.translateMessage(msg, types.ERROR, args));
        },
        warning: function(msg, args) {
            Notification.warning(this.translateMessage(msg, types.WARNING, args));
        },
        info: function(msg, args) {
            Notification.info(this.translateMessage(msg, types.INFO, args));
        },
        success: function(msg, args) {
            Notification.success(this.translateMessage(msg, types.SUCCESS, args));
        }
    });

    return new NotificacaoService();

}]).config(function(NotificationProvider) {
    NotificationProvider.setOptions({
        delay: 5 * 1000,
        startTop: 60,
        startRight: 10,
        verticalSpacing: 20,
        horizontalSpacing: 20,
        positionX: 'right',
        positionY: 'top'
    });
});

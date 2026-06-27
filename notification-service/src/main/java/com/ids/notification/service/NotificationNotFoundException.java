package com.ids.notification.service;

public class NotificationNotFoundException extends RuntimeException {

    public NotificationNotFoundException(Long id) {
        super("No se encontro la notificacion con id: " + id);
    }
}
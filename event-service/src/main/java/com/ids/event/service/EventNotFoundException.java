package com.ids.event.service;


public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Long id) {
        super("No se encontro el evento con id: " + id);
    }
}
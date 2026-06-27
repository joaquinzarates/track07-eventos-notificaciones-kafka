package com.ids.notification.kafka;

import com.ids.notification.model.Notification;
import com.ids.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SystemEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "system-events", groupId = "notification-service-group")
    public void escuchar(SystemEventMessage mensaje) {
        log.info("Evento recibido desde 'system-events': {}", mensaje);

        Notification.Canal canal = determinarCanal(mensaje.getNivel());
        String mensajeNotificacion = construirMensaje(mensaje);

        notificationService.procesarEvento(
                mensaje.getEventId().toString(),
                canal,
                mensajeNotificacion
        );
    }

  
    private Notification.Canal determinarCanal(String nivel) {
        return switch (nivel) {
            case "ERROR" -> Notification.Canal.SMS;
            case "WARN" -> Notification.Canal.EMAIL;
            default -> Notification.Canal.LOG;
        };
    }

    private String construirMensaje(SystemEventMessage evento) {
        return String.format(
                "[%s] Evento %s desde '%s': %s",
                evento.getNivel(),
                evento.getTipo(),
                evento.getOrigen(),
                evento.getDescripcion()
        );
    }
}
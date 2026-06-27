package com.ids.notification.service;

import com.ids.notification.model.Notification;
import com.ids.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

   
    private final NotificationSender notificationSender;

    public Notification procesarEvento(String eventId, Notification.Canal canal, String mensaje) {
        Notification notification = new Notification();
        notification.setEventId(eventId);
        notification.setCanal(canal);
        notification.setMensaje(mensaje);
        notification.setTimestamp(LocalDateTime.now(ZoneOffset.UTC));

       
        boolean exito = notificationSender.enviarNotificacionSimulada(mensaje);
        notification.setEnviada(exito);

        return notificationRepository.save(notification);
    }

    public List<Notification> listarTodas() {
        return notificationRepository.findAll();
    }

    public Notification buscarPorId(Long id) {
        return notificationRepository.findById(id)
                .orElseThrow(() -> new NotificationNotFoundException(id));
    }

    public List<Notification> filtrarPorEnviada(Boolean enviada) {
        return notificationRepository.findByEnviada(enviada);
    }
}
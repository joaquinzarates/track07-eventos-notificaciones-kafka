package com.ids.event.service;

import com.ids.event.controller.EventRequest;
import com.ids.event.kafka.SystemEventMessage;
import com.ids.event.kafka.SystemEventProducer;
import com.ids.event.model.SystemEvent;
import com.ids.event.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final SystemEventProducer systemEventProducer;

   
    public SystemEvent registrarEvento(EventRequest request) {
        SystemEvent event = new SystemEvent();
        event.setTipo(request.getTipo());
        event.setDescripcion(request.getDescripcion());
        event.setOrigen(request.getOrigen());
        event.setNivel(request.getNivel());
        event.setTimestamp(LocalDateTime.now()); // autogenerado por el servidor

        SystemEvent guardado = eventRepository.save(event);

        SystemEventMessage mensaje = SystemEventMessage.fromEntity(guardado);
        systemEventProducer.publicarEvento(mensaje);

        return guardado;
    }

    public List<SystemEvent> listarTodos() {
        return eventRepository.findAll();
    }

    public SystemEvent buscarPorId(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new EventNotFoundException(id));
    }

    public List<SystemEvent> filtrarPorTipo(SystemEvent.TipoEvento tipo) {
        return eventRepository.findByTipo(tipo);
    }
}
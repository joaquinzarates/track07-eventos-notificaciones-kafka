package com.ids.event.controller;

import com.ids.event.model.SystemEvent;
import com.ids.event.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
@Tag(name = "Events", description = "Registro y consulta de eventos del sistema")
public class EventController {

    private final EventService eventService;

    @PostMapping
    @Operation(summary = "Registra un nuevo evento y lo publica en Kafka (topic system-events)")
    @ApiResponse(responseCode = "201", description = "Evento creado correctamente")
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    public ResponseEntity<SystemEvent> crearEvento(@Valid @RequestBody EventRequest request) {
        SystemEvent creado = eventService.registrarEvento(request);
    
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping
    @Operation(summary = "Lista todos los eventos registrados, opcionalmente filtrados por tipo")
    public ResponseEntity<List<SystemEvent>> listarEventos(
            @RequestParam(required = false) SystemEvent.TipoEvento tipo) {
 if (tipo != null) {
            return ResponseEntity.ok(eventService.filtrarPorTipo(tipo));
        }
        return ResponseEntity.ok(eventService.listarTodos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un evento por id")
    @ApiResponse(responseCode = "200", description = "Evento encontrado")
    @ApiResponse(responseCode = "404", description = "No existe un evento con ese id")
    public ResponseEntity<SystemEvent> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.buscarPorId(id));
    }
}
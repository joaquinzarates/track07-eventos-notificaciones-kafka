package com.ids.notification.controller;

import com.ids.notification.model.Notification;
import com.ids.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Tag(name = "Notifications", description = "Consulta de notificaciones generadas a partir de eventos")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    @Operation(summary = "Lista todas las notificaciones, opcionalmente filtradas por estado de envio")
    public ResponseEntity<List<Notification>> listarNotificaciones(
            @RequestParam(required = false) Boolean enviada) {

        if (enviada != null) {
            return ResponseEntity.ok(notificationService.filtrarPorEnviada(enviada));
        }
        return ResponseEntity.ok(notificationService.listarTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una notificacion por id")
    @ApiResponse(responseCode = "200", description = "Notificacion encontrada")
    @ApiResponse(responseCode = "404", description = "No existe una notificacion con ese id")
    public ResponseEntity<Notification> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.buscarPorId(id));
    }
}
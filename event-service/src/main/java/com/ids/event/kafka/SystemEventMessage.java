package com.ids.event.kafka;

import com.ids.event.model.SystemEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemEventMessage {

    private Long eventId;
    private SystemEvent.TipoEvento tipo;
    private String descripcion;
    private String origen;
    private SystemEvent.NivelEvento nivel;
    private LocalDateTime timestamp;

    public static SystemEventMessage fromEntity(SystemEvent event) {
        return new SystemEventMessage(
                event.getId(),
                event.getTipo(),
                event.getDescripcion(),
                event.getOrigen(),
                event.getNivel(),
                event.getTimestamp()
        );
    }
}
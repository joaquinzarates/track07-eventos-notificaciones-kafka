package com.ids.notification.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemEventMessage {

    private Long eventId;
    private String tipo;       
    private String descripcion;
    private String origen;
    private String nivel;      
    private LocalDateTime timestamp;
}
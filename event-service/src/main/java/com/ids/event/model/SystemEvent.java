package com.ids.event.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SystemEvent {

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)

    @NotNull(message = "El tipo es obligatorio")
    private TipoEvento tipo;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @Enumerated(EnumType.STRING)
    private NivelEvento nivel;

    private LocalDateTime timestamp;

    public enum TipoEvento {
        CREACION, ACTUALIZACION, ELIMINACION, ALERTA
    }

    public enum NivelEvento {
        INFO, WARN, ERROR
    }
}
package com.ids.event.controller;

import com.ids.event.model.SystemEvent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EventRequest {

    @NotNull(message = "El tipo es obligatorio")
    private SystemEvent.TipoEvento tipo;

    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;

    @NotBlank(message = "El origen es obligatorio")
    private String origen;

    @NotNull(message = "El nivel es obligatorio")
    private SystemEvent.NivelEvento nivel;
}
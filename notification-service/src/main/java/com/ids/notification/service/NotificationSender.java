package com.ids.notification.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Random;


@Component
@Slf4j
public class NotificationSender {


    private final Random random = new Random();

  
    @CircuitBreaker(name = "envioNotificacion", fallbackMethod = "fallbackEnvio")
    public boolean enviarNotificacionSimulada(String mensaje) {
      
        if (random.nextInt(100) < 40) {
            throw new RuntimeException("Fallo simulado en el envio de la notificacion");
        }

        log.info("Notificacion enviada (simulada): {}", mensaje);
        return true;
    }

    
    private boolean fallbackEnvio(String mensaje, Throwable t) {
        log.warn("Fallback activado (Circuit Breaker abierto o fallo real). Causa: {}",
                t.getMessage());
        return false;
    }
}
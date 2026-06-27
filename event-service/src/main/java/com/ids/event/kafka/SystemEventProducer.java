package com.ids.event.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
@Slf4j
public class SystemEventProducer {

    private static final String TOPIC = "system-events";

  private final KafkaTemplate<String, SystemEventMessage> kafkaTemplate;

    public void publicarEvento(SystemEventMessage mensaje) {
        log.info("Publicando evento en topic '{}': {}", TOPIC, mensaje);

 kafkaTemplate.send(TOPIC, mensaje.getEventId().toString(), mensaje);
    }
}
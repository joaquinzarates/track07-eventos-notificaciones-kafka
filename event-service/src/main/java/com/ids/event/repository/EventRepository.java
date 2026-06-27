package com.ids.event.repository;

import com.ids.event.model.SystemEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<SystemEvent, Long> {

    List<SystemEvent> findByTipo(SystemEvent.TipoEvento tipo);
}
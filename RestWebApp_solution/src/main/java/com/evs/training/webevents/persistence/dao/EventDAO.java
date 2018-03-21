package com.evs.training.webevents.persistence.dao;

import com.evs.training.webevents.persistence.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventDAO extends JpaRepository<Event, UUID> {
}

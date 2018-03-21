package com.evs.training.webevents.persistence.dao;

import com.evs.training.webevents.controlers.payloads.Event;

import java.util.Optional;
import java.util.UUID;

public interface SimpleEventDAO {

  Optional<Event> deleteEventMetaData(UUID eventId, UUID metaDataId);
}

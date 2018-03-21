package com.evs.training.webevents.persistence.dao.jpa;

import com.evs.training.webevents.controlers.mappers.EventMapper;
import com.evs.training.webevents.controlers.payloads.Event;
import com.evs.training.webevents.persistence.dao.SimpleEventDAO;
import com.evs.training.webevents.persistence.entities.MetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JPASimpleEventDAO implements SimpleEventDAO {

  @PersistenceContext
  EntityManager em;

  @Autowired
  EventMapper eventMapper;

  @Override
  public Optional<Event> deleteEventMetaData(UUID eventId, UUID metaDataId) {
    MetaData metaData = em.find(MetaData.class, metaDataId);
    if (metaData != null && metaData.getEvent().getId().equals(eventId)) {
      com.evs.training.webevents.persistence.entities.Event eventEntity = em.find(com.evs.training.webevents.persistence.entities.Event.class, eventId);
      if (eventEntity != null) {
        eventEntity.getMetaData().remove(metaData);
        return Optional.ofNullable(eventMapper.map(em.merge(eventEntity)));
      }
    }
    return Optional.empty();
  }
}

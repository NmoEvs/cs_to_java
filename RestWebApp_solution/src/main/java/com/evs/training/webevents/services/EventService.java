package com.evs.training.webevents.services;

import com.evs.training.webevents.controlers.mappers.EventMapper;
import com.evs.training.webevents.controlers.payloads.Event;
import com.evs.training.webevents.controlers.payloads.MetaData;
import com.evs.training.webevents.persistence.dao.EventDAO;
import com.evs.training.webevents.persistence.dao.SimpleEventDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventService {

  @Autowired
  private EventMapper eventMapper;

  @Autowired
  private EventDAO eventDAO;

  @Autowired
  private SimpleEventDAO simpleEventDAO;

  public Event getEventById(UUID id){
    return eventMapper.map(eventDAO.findOne(id));
  }

  @Transactional
  public Event createEvent(Event event) {
    return eventMapper.map(eventDAO.save(eventMapper.map(event)));
  }

  @Transactional
  public void deleteEvent(UUID eventId) {
    eventDAO.delete(eventId);
  }

  @Transactional
  public Optional<Event> deleteEventMetaData(UUID eventId, UUID metaDataId) {
    return simpleEventDAO.deleteEventMetaData(eventId, metaDataId);
  }

  @Transactional
  public Event addEventMetaData(UUID eventId, MetaData metaData) {
    com.evs.training.webevents.persistence.entities.Event eventEntity = eventDAO.findOne(eventId);
    com.evs.training.webevents.persistence.entities.MetaData metaDataEntity = eventMapper.map(metaData);
    metaDataEntity.setEvent(eventEntity);
    eventEntity.getMetaData().add(metaDataEntity);
    return eventMapper.map(eventDAO.save(eventEntity));
  }

  public List<Event> getAllEvents() {
    List<com.evs.training.webevents.persistence.entities.Event> entities = eventDAO.findAll();
    List<Event> events = new ArrayList<>();
    entities.parallelStream().forEach(event -> events.add(eventMapper.map(event)));
    return events;
  }
}

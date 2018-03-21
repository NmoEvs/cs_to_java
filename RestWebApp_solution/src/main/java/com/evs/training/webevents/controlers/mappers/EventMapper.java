package com.evs.training.webevents.controlers.mappers;

import com.evs.training.webevents.controlers.payloads.Event;
import com.evs.training.webevents.controlers.payloads.MetaData;
import org.springframework.stereotype.Service;

@Service
public class EventMapper {

  public Event map(com.evs.training.webevents.persistence.entities.Event eventEntity) {
    if (eventEntity == null){
      return null;
    }
    Event event = new Event(eventEntity.getId(), eventEntity.getName(), eventEntity.getDescription(), eventEntity.getEventDate());
    eventEntity.getMetaData().forEach(m -> event.getMetaData().add(map(m)));
    return event;
  }

  public MetaData map(com.evs.training.webevents.persistence.entities.MetaData metaDataEntity) {
    return new MetaData(metaDataEntity.getId(), metaDataEntity.getComment());
  }

  public com.evs.training.webevents.persistence.entities.Event map(Event event) {
    if (event == null){
      return null;
    }
    com.evs.training.webevents.persistence.entities.Event eventEntity =
        new com.evs.training.webevents.persistence.entities.Event(event.getUuid(), event.getName(), event.getDescription(), event.getEventDate());
    event.getMetaData().forEach(m -> {
      com.evs.training.webevents.persistence.entities.MetaData metaDataEntity = map(m);
      metaDataEntity.setEvent(eventEntity);
      eventEntity.getMetaData().add(metaDataEntity);
    });
    return eventEntity;
  }

  public com.evs.training.webevents.persistence.entities.MetaData map(MetaData metaData) {
    return new com.evs.training.webevents.persistence.entities.MetaData(metaData.getId(), metaData.getComment());
  }

}

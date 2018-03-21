package com.evs.training.webevents.controlers;

import com.evs.training.webevents.controlers.exceptions.NotFoundException;
import com.evs.training.webevents.controlers.payloads.Event;
import com.evs.training.webevents.controlers.payloads.MetaData;
import com.evs.training.webevents.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventsController {

  @Autowired
  EventService eventService;

  @GetMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Event getEventById(@PathVariable("eventId") UUID eventId) {
    Event event = eventService.getEventById(eventId);
    if (event == null){
      throw new NotFoundException();
    }
    return event;
  }

  @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<Event> getAllEvents() {
    return eventService.getAllEvents();
  }

  @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Event createEvent(@RequestBody Event event) {
    return eventService.createEvent(event);
  }

  @DeleteMapping(value = "/{eventId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public void deleteEvent(@PathVariable("eventId") UUID eventId) {
    eventService.deleteEvent(eventId);
  }

  @DeleteMapping(value = "/{eventId}/{metaDataId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Event deleteMetadataEvent(@PathVariable("eventId") UUID eventId, @PathVariable("metaDataId") UUID metaDataId) {
    return eventService.deleteEventMetaData(eventId,metaDataId).orElseThrow(NotFoundException::new);
  }

  @PostMapping(value = "/{eventId}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  @ResponseStatus(HttpStatus.ACCEPTED)
  public Event addMetadataEvent(@PathVariable("eventId") UUID eventId, @RequestBody MetaData metaData) {
    Event event = eventService.addEventMetaData(eventId, metaData);
    if (event == null){
      throw new NotFoundException();
    }
    return event;
  }

}

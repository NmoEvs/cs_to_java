package com.evs.training.webevents.controlers.payloads;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Event {

  private UUID uuid;
  private String name;
  private String description;
  private LocalDateTime eventDate;
  private Set<MetaData> metaData = new HashSet<>();

  public Event() {
    //Public constructor must exists for JSON marshalling
  }

  public Event(UUID uuid, String name, String description, LocalDateTime eventDate) {
    this.uuid = uuid;
    this.name = name;
    this.description = description;
    this.eventDate = eventDate;
  }

  public UUID getUuid() {
    return uuid;
  }

  public void setUuid(UUID uuid) {
    this.uuid = uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public LocalDateTime getEventDate() {
    return eventDate;
  }

  public void setEventDate(LocalDateTime eventDate) {
    this.eventDate = eventDate;
  }

  public Set<MetaData> getMetaData() {
    return metaData;
  }

  public void setMetaData(Set<MetaData> metaData) {
    this.metaData = metaData;
  }
}

package com.evs.training.webevents.persistence.entities;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Event {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @Column
  private String name;
  @Column
  private String description;
  @Column
  private LocalDateTime eventDate;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true,  mappedBy = "event")
  @Fetch(FetchMode.JOIN)
  private Set<MetaData> metaData = new HashSet<>();
  @Version
  private Long version;

  public Event() {
  }

  public Event(UUID id, String name, String description, LocalDateTime eventDate) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.eventDate = eventDate;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

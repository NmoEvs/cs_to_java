package com.evs.training.webevents.persistence.entities;

import javax.persistence.*;
import java.util.UUID;

@Entity
public class MetaData {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  @ManyToOne(optional = false)
  @JoinColumn(name = "event_id")
  private Event event;
  @Column
  private String comment;
  @Version
  private Long version;

  public MetaData() {
  }

  public MetaData(UUID id, String comment) {
    this.id = id;
    this.comment = comment;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Event getEvent() {
    return event;
  }

  public void setEvent(Event event) {
    this.event = event;
  }
}

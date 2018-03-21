package com.evs.training.webevents.controlers.payloads;

import java.util.UUID;

public class MetaData {

  private UUID id;
  private String comment;

  public MetaData() {
    //Public constructor must exists for JSON marshalling
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
}

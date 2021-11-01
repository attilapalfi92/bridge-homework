package com.getbridge.homework.model;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class OneOneOneRequest {
  private String title;
  private String description;
  private long participantId1;
  private long participantId2;
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private LocalDateTime plannedDate;
  private String location;
  private boolean closed;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public long getParticipantId1() {
    return participantId1;
  }

  public void setParticipantId1(long participantId1) {
    this.participantId1 = participantId1;
  }

  public long getParticipantId2() {
    return participantId2;
  }

  public void setParticipantId2(long participantId2) {
    this.participantId2 = participantId2;
  }

  public LocalDateTime getPlannedDate() {
    return plannedDate;
  }

  public void setPlannedDate(LocalDateTime plannedDate) {
    this.plannedDate = plannedDate;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public boolean isClosed() {
    return closed;
  }

  public void setClosed(boolean closed) {
    this.closed = closed;
  }
}

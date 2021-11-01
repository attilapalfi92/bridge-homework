package com.getbridge.homework.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name = "OneOnOne")
public class OneOnOneEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String description;

  @OneToOne
  @JoinColumn(name = "participant_1")
  private EmployeeEntity participant1;

  @OneToOne
  @JoinColumn(name = "participant_2")
  private EmployeeEntity participant2;

  private Timestamp plannedDate;
  private String location;
  private boolean closed;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public EmployeeEntity getParticipant1() {
    return participant1;
  }

  public void setParticipant1(EmployeeEntity participant1) {
    this.participant1 = participant1;
  }

  public EmployeeEntity getParticipant2() {
    return participant2;
  }

  public void setParticipant2(EmployeeEntity participant2) {
    this.participant2 = participant2;
  }

  public Timestamp getPlannedDate() {
    return plannedDate;
  }

  public void setPlannedDate(Timestamp plannedDate) {
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

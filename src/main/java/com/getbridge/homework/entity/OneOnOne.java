package com.getbridge.homework.entity;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class OneOnOne {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String title;

  private String description;

  @OneToOne
  @JoinColumn(name = "participant_1")
  private Employee participant1;

  @OneToOne
  @JoinColumn(name = "participant_2")
  private Employee participant2;

  //  @Column(name = "planned_date")
  private Timestamp plannedDate;

  //  @Column(name = "location")
  private String location;

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public Employee getParticipant1() {
    return participant1;
  }

  public void setParticipant1(Employee participant1) {
    this.participant1 = participant1;
  }

  public Employee getParticipant2() {
    return participant2;
  }

  public void setParticipant2(Employee participant2) {
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
}

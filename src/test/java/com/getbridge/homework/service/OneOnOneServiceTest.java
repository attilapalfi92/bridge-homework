package com.getbridge.homework.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import com.getbridge.homework.entity.EmployeeEntity;
import com.getbridge.homework.entity.OneOnOneEntity;
import com.getbridge.homework.model.OneOneOneRequest;
import com.getbridge.homework.repository.EmployeeRepository;
import com.getbridge.homework.repository.OneOnOneRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.server.ResponseStatusException;

class OneOnOneServiceTest {

  private static final long ID = 1;
  private static final long PARTICIPANT_ID_1 = 11;
  private static final long PARTICIPANT_ID_2 = 12;

  private final OneOnOneRepository oneOnOneRepository = Mockito.mock(OneOnOneRepository.class);
  private final EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);

  private final OneOnOneService underTest = new OneOnOneService(oneOnOneRepository, employeeRepository);

  @Test
  void testUpdateHappyPath() {
    OneOneOneRequest request = getRequest();
    EmployeeEntity employee1 = getEmployee(PARTICIPANT_ID_1);
    EmployeeEntity employee2 = getEmployee(PARTICIPANT_ID_2);
    OneOnOneEntity entity = getEntity(employee1, employee2);

    when(oneOnOneRepository.findById(ID)).thenReturn(Optional.of(entity));
    when(employeeRepository.findById(PARTICIPANT_ID_1)).thenReturn(Optional.of(employee1));
    when(employeeRepository.findById(PARTICIPANT_ID_2)).thenReturn(Optional.of(employee2));
    when(oneOnOneRepository.save(any())).thenReturn(entity);

    underTest.update(ID, request);

    Mockito.verify(oneOnOneRepository, times(1)).save(any());
  }

  @Test
  void testUpdateOneOnOneNotFound() {
    OneOneOneRequest request = getRequest();

    when(oneOnOneRepository.findById(ID)).thenReturn(Optional.empty());

    Assertions.assertThrows(ResponseStatusException.class, () -> underTest.update(ID, request));
  }

  @Test
  void testUpdateOneOnOneClosed() {
    OneOneOneRequest request = getRequest();
    EmployeeEntity employee1 = getEmployee(PARTICIPANT_ID_1);
    EmployeeEntity employee2 = getEmployee(PARTICIPANT_ID_2);
    OneOnOneEntity entity = getEntity(employee1, employee2);
    entity.setClosed(true);

    when(oneOnOneRepository.findById(ID)).thenReturn(Optional.of(entity));

    Assertions.assertThrows(ResponseStatusException.class, () -> underTest.update(ID, request));
  }

  private OneOnOneEntity getEntity(EmployeeEntity employee1, EmployeeEntity employee2) {
    OneOnOneEntity entity = new OneOnOneEntity();
    entity.setId(ID);
    entity.setClosed(false);
    entity.setPlannedDate(Timestamp.from(Instant.now()));
    entity.setParticipant1(employee1);
    entity.setParticipant2(employee2);
    return entity;
  }

  private OneOneOneRequest getRequest() {
    OneOneOneRequest request = new OneOneOneRequest();
    request.setParticipantId1(PARTICIPANT_ID_1);
    request.setParticipantId2(PARTICIPANT_ID_2);
    request.setPlannedDate(LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault()));
    return request;
  }

  private EmployeeEntity getEmployee(long id) {
    EmployeeEntity employee = new EmployeeEntity();
    employee.setId(id);
    return employee;
  }
}
package com.getbridge.homework.service;

import com.getbridge.homework.entity.EmployeeEntity;
import com.getbridge.homework.entity.OneOnOneEntity;
import com.getbridge.homework.model.OneOneOneRequest;
import com.getbridge.homework.model.OneOneOneResponse;
import com.getbridge.homework.repository.EmployeeRepository;
import com.getbridge.homework.repository.OneOnOneRepository;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class OneOnOneService {

  private final OneOnOneRepository oneOnOneRepository;
  private final EmployeeRepository employeeRepository;

  public OneOnOneService(OneOnOneRepository oneOnOneRepository, EmployeeRepository employeeRepository) {
    this.oneOnOneRepository = oneOnOneRepository;
    this.employeeRepository = employeeRepository;
  }

  public List<OneOneOneResponse> list() {
    return StreamSupport.stream(oneOnOneRepository.findAll().spliterator(), false)
        .map(this::entityToResponse)
        .collect(Collectors.toList());
  }

  public Optional<OneOneOneResponse> get(long id) {
    return oneOnOneRepository.findById(id).map(this::entityToResponse);

  }

  public OneOneOneResponse save(OneOneOneRequest oneOnOne) {
    return entityToResponse(oneOnOneRepository.save(requestToEntity(oneOnOne)));
  }

  public OneOneOneResponse update(long id, OneOneOneRequest update) {
    Optional<OneOnOneEntity> oneOnOne = oneOnOneRepository.findById(id);
    if (oneOnOne.isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
    return entityToResponse(oneOnOneRepository.save(requestToEntity(update)));
  }

  private OneOnOneEntity requestToEntity(OneOneOneRequest request) {
    OneOnOneEntity entity = new OneOnOneEntity();
    entity.setDescription(request.getDescription());
    entity.setLocation(request.getLocation());
    EmployeeEntity participant1 = employeeRepository.findById(request.getParticipantId1())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, request.getParticipantId1() + " not found"));
    EmployeeEntity participant2 = employeeRepository.findById(request.getParticipantId2())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, request.getParticipantId2() + " not found"));
    entity.setParticipant1(participant1);
    entity.setParticipant2(participant2);
    entity.setPlannedDate(Timestamp.from(request.getPlannedDate().toInstant(ZoneOffset.UTC)));
    entity.setTitle(request.getTitle());
    return entity;
  }

  private OneOneOneResponse entityToResponse(OneOnOneEntity entity) {
    OneOneOneResponse response = new OneOneOneResponse();
    response.setId(entity.getId());
    response.setDescription(entity.getDescription());
    response.setLocation(entity.getLocation());
    response.setPlannedDate(LocalDateTime.ofInstant(entity.getPlannedDate().toInstant(), ZoneOffset.UTC));
    response.setTitle(entity.getTitle());
    response.setParticipantId1(entity.getParticipant1().getId());
    response.setParticipantId2(entity.getParticipant2().getId());
    return response;
  }
}

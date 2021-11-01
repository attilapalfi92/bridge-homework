package com.getbridge.homework.controller;

import com.getbridge.homework.model.OneOneOneRequest;
import com.getbridge.homework.model.OneOneOneResponse;
import com.getbridge.homework.service.OneOnOneService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OneOnOneController {

  private final OneOnOneService oneOnOneService;

  public OneOnOneController(OneOnOneService oneOnOneService) {
    this.oneOnOneService = oneOnOneService;
  }

  @GetMapping("/one-on-one")
  public List<OneOneOneResponse> list() {
    return oneOnOneService.list();
  }

  @GetMapping("/one-on-one/search/{closed}")
  public List<OneOneOneResponse> search(@PathVariable("closed") boolean closed) {
    return oneOnOneService.search(closed);
  }

  @GetMapping("/one-on-one/{id}")
  public ResponseEntity<OneOneOneResponse> get(@PathVariable("id") long id) {
    return oneOnOneService
        .get(id)
        .map(ResponseEntity::ok)
        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/one-on-one")
  public ResponseEntity<OneOneOneResponse> create(OneOneOneRequest oneOnOne) {
    return ResponseEntity.ok(oneOnOneService.save(oneOnOne));
  }

  @PutMapping("/one-on-one/{id}")
  public ResponseEntity<OneOneOneResponse> update(@PathVariable("id") long id, OneOneOneRequest oneOnOne) {
    return ResponseEntity.ok(oneOnOneService.update(id, oneOnOne));
  }
}

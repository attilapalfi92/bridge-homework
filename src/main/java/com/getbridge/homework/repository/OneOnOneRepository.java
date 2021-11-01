package com.getbridge.homework.repository;

import com.getbridge.homework.entity.OneOnOneEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface OneOnOneRepository extends CrudRepository<OneOnOneEntity, Long> {
  List<OneOnOneEntity> findByClosed(boolean closed);
}

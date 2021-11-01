package com.getbridge.homework.repository;

import com.getbridge.homework.entity.OneOnOne;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "one-on-one", path = "one-on-one")
public interface OneOnOneRepository extends PagingAndSortingRepository<OneOnOne, Long> {

}

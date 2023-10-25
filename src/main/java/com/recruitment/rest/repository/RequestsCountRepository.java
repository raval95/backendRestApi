package com.recruitment.rest.repository;

import com.recruitment.rest.model.RequestsCount;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsCountRepository extends MongoRepository<RequestsCount, Long> {
}

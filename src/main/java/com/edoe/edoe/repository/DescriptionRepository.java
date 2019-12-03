package com.edoe.edoe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edoe.edoe.models.Description;

public interface DescriptionRepository extends MongoRepository<Description, Long> {
	Description findByDescription(String description);
}

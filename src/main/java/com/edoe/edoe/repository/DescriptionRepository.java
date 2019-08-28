package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edoe.edoe.models.Description;

@Repository
public interface DescriptionRepository extends JpaRepository<Description, Long> {
	Description findByDescription(String description);
}

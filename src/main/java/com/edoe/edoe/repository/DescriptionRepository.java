package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edoe.edoe.models.Description;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
	Description findByDescription(String description);
}

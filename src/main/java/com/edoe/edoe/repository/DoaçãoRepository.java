package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edoe.edoe.models.Doação;

public interface DoaçãoRepository extends JpaRepository<Doação, Long> {
	
}

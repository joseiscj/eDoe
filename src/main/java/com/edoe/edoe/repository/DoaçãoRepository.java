package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edoe.edoe.models.Doação;

@Repository
public interface DoaçãoRepository extends JpaRepository<Doação, Long> {
	
}

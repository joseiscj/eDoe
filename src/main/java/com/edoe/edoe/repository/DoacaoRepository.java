package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edoe.edoe.models.Doacao;

@Repository
public interface DoacaoRepository extends JpaRepository<Doacao, Long> {
	
}

package com.edoe.edoe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edoe.edoe.models.Doacao;

public interface DoacaoRepository extends MongoRepository<Doacao, Long> {
	
}

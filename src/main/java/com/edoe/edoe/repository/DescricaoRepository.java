package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edoe.edoe.models.Descricao;

public interface DescricaoRepository extends JpaRepository<Descricao, Long> {
	Descricao findByDescricao(String descricao);
}

package com.edoe.edoe.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edoe.edoe.models.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, Long>{
	Usuario findById(long id);
	Usuario findByIdentificacao(String identification);
	Usuario findByNome(String nome);
	Usuario findByUsername(String username);
}

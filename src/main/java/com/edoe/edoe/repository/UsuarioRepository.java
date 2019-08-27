package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edoe.edoe.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findById(long id);
	Usuario findByIdentificacao(String identification);
	Usuario findByNome(String nome);

}

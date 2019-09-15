package com.edoe.edoe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edoe.edoe.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	Usuario findById(long id);
	Usuario findByIdentificacao(String identification);
	Usuario findByNome(String nome);
	Usuario findByUsername(String username);
}

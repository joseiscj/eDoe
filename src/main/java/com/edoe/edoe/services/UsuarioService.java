package com.edoe.edoe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.edoe.edoe.dto.UsuarioDTO;
import com.edoe.edoe.models.Tipo;
import com.edoe.edoe.models.Usuario;
import com.edoe.edoe.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	public Usuario createDoador(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioDTO.getUsuario();
		usuario.setTipo(Tipo.DOADOR);
		return usuarioRepository.save(usuario);
	}
	
	public Usuario createReceptor(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioRepository.findByIdentificacao(usuarioDTO.getIdentificacao());
		
		if (usuario == null) {
			usuarioDTO.setTipo(Tipo.DOADOR);
			return usuarioRepository.save(usuarioDTO.getUsuario());
		} else {
			return usuario;
		}
	}
	
	public Usuario findByIdentificacao(String identificacao) {
		return usuarioRepository.findByIdentificacao(identificacao);
	}
	
	public Usuario findByName(String nome) {
		return usuarioRepository.findByNome(nome);
	}
	
	public Usuario update(String identificacao, String nome, String email, String celular) {
		Usuario usuario = findByIdentificacao(identificacao);
		if (nome != null && !nome.isEmpty()) usuario.setNome(nome);
		if (email != null && !email.isEmpty()) usuario.setEmail(email);
		if (celular != null && !celular.isEmpty()) usuario.setCelular(celular);
		return usuarioRepository.save(usuario);
	}
	
	public void delete(String identificacao) {
		Usuario usuario = usuarioRepository.findByIdentificacao(identificacao);
		usuarioRepository.delete(usuario);
	}
	@Cacheable(value="user-cache", key="#root.method.name")
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	@Cacheable(value="user-cache", key= "'UserCache'+#userId")
	public Usuario findById(long userId) {
		return usuarioRepository.findById(userId);
	}
	
	public void delete(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioDTO.getUsuario();
		usuarioRepository.delete(usuario);
	}
	
	public Usuario update(UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioDTO.getUsuario();
		return usuarioRepository.save(usuario);
	}	
	
	
}

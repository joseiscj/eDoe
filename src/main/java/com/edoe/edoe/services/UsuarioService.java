package com.edoe.edoe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setCelular(celular);
		return usuario;
	}
	
	public void delete(String identificacao) {
		Usuario usuario = usuarioRepository.findByIdentificacao(identificacao);
		usuarioRepository.delete(usuario);
	}
	
	
	public List<Usuario> findAll(){
		return usuarioRepository.findAll();
	}
	
	public Usuario findById(long id) {
		return usuarioRepository.findById(id);
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

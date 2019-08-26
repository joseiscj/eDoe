package com.edoe.edoe.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edoe.edoe.dto.UsuarioDTO;
import com.edoe.edoe.models.Usuario;
import com.edoe.edoe.services.UsuarioService;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/")
	public List<Usuario> getUsuarios() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/{id}")
	public Usuario getUsuario(@PathVariable(value="id") long id) {
		return usuarioService.findById(id);
	}
	
	//pequisar usuarios pelo documento de identificacao
	@GetMapping("/{identificacao}")
	public Usuario getUsuario(@PathVariable(value="identificacao") String identificacao) {
		return usuarioService.findByIdentificacao(identificacao);
	}
	
	//pequisar usuarios pelo nome completo
	@GetMapping("/{nome}")
	public Usuario getUsuarioPorNome(@PathVariable(value="nome") String nome) {
		return usuarioService.findByNome(nome);
	}
	
	@PostMapping("/usuario")
	public Usuario cadastrarDoadores(@RequestBody UsuarioDTO usuarioDTO) {
		return usuarioService.createDoador(usuarioDTO);
	}
	
	//remover usuários do sistema localizados pelo seu documento de identificação
	@DeleteMapping("/")
	public void delete(String identificacao) {
		usuarioService.delete(identificacao);
	}
	
	/*
	 * //cadastrar novos usuários receptores via leitura de arquivo public void
	 * cadastraReceptores(String novosReceptores) throws IOException { Scanner sc =
	 * new Scanner(new File(novosReceptores)); String linha = null;
	 * while(sc.hasNextLine()) { linha = sc.nextLine(); String[] dadosUsuario =
	 * linha.split(","); if (dadosUsuario.length != 4) { throw new
	 * IOException("Campos inválidos"); } Usuario usuario = new
	 * Usuario(dadosUsuario[0], dadosUsuario[1], dadosUsuario[2], dadosUsuario[3],
	 * dadosUsuario[4])); } }
	 */
	
	//atualizar nome, email e celular de usuário pelo documento de identificacao
	@PutMapping("/usuario")
	public Usuario put(String identificacao, String nome, String email, String celular) {
		return usuarioService.update(identificacao, nome, email, celular);
	}
}

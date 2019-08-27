package com.edoe.edoe.controllers;

import java.io.File;
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
import com.edoe.edoe.models.Classe;
import com.edoe.edoe.models.Tipo;
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
	
	@PostMapping("/")
	public Usuario cadastrarDoadores(@RequestBody UsuarioDTO usuarioDTO) {
		return usuarioService.createDoador(usuarioDTO);
	}
	
	//remover usuários do sistema localizados pelo seu documento de identificação
	@DeleteMapping("/")
	public void delete(String identificacao) {
		usuarioService.delete(identificacao);
	}
	
	@PostMapping("/receptores")
	public void cadastraReceptores(@RequestBody String nomeDoArquivoCsv) throws IOException {
		Scanner sc = new Scanner(new File(nomeDoArquivoCsv));
		String linha = null;
		while (sc.hasNextLine()) {
			linha = sc.nextLine();
			
			if(linha.equals("id,nome,e-mail,celular,classe")) {
				continue;
			}
			String[] dadosTriangulo = linha.split(",");
			
			UsuarioDTO usuarioDTO = new UsuarioDTO(dadosTriangulo[1], dadosTriangulo[2], dadosTriangulo[3], getClasse(dadosTriangulo[4]), dadosTriangulo[0], Tipo.RECEPTOR);
			usuarioService.createReceptor(usuarioDTO);
		}
		sc.close();
	}
	
	private Classe getClasse(String classe) {
		switch (classe) {
			case "PESSOA_FISICA": 
				return Classe.PESSOA_FISICA;
			case "IGREJA":
				return Classe.IGREJA;
			case "ORGAO_PUBLICO_MUNICIPAL":
				return Classe.ORGAO_PUBLICO_MUNICIPAL;
			case "ORGAO_PUBLICO_ESTADUAL":
				return Classe.ORGAO_PUBLICO_ESTADUAL;
			case "ORGAO_PUBLICO_FEDERAL":
				return Classe.ORGAO_PUBLICO_FEDERAL;
			case "ONG":
				return Classe.ONG;
			case "ASSOCIACAO":
				return Classe.ASSOCIACAO;
			case "SOCIEDADE":
				return Classe.SOCIEDADE;
		}
		return null;
	}
	
	//atualizar nome, email e celular de usuário pelo documento de identificacao
	@PutMapping("/")
	public Usuario put(String identificacao, String nome, String email, String celular) {
		return usuarioService.update(identificacao, nome, email, celular);
	}
}

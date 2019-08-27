package com.edoe.edoe.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<Usuario>> getUsers() {
		try {
			return new ResponseEntity<List<Usuario>>(usuarioService.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getUser(@PathVariable(value="id") long id) {
		try {
			return new ResponseEntity<Usuario>(usuarioService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//pequisar usuarios pelo documento de identificacao
	@GetMapping("/{identification}")
	public ResponseEntity<Usuario> getUser(@PathVariable(value="identification") String identification) {
		try {
			return new ResponseEntity<Usuario>(usuarioService.findByIdentificacao(identification), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//pequisar usuarios pelo nome completo
	@GetMapping("/{name}")
	public ResponseEntity<Usuario> getUserPerName(@PathVariable(value="nome") String name) {
		try {
			return new ResponseEntity<Usuario>(usuarioService.findByName(name), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<Usuario> createDoador(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			return new ResponseEntity<Usuario>(usuarioService.createDoador(usuarioDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//remover usuários do sistema localizados pelo seu documento de identificação
	@DeleteMapping("/")
	public ResponseEntity<Object> delete(String identification) {
		try {
			usuarioService.delete(identification);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/receptores")
	public void createReceptor(@RequestBody String CSVName) throws IOException {
		Scanner sc = new Scanner(new File(CSVName));
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
	public ResponseEntity<Usuario> put(String identificacao, String nome, String email, String celular) {
		try {
			return new ResponseEntity<>(usuarioService.update(identificacao, nome, email, celular), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}

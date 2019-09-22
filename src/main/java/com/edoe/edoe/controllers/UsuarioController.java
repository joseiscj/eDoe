package com.edoe.edoe.controllers;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edoe.edoe.dto.UsuarioDTO;
import com.edoe.edoe.models.Classe;
import com.edoe.edoe.models.Usuario;
import com.edoe.edoe.services.UsuarioService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping("/")
	public ResponseEntity get(@RequestParam(value="identification", required = false) Optional<String> identification, @RequestParam(value="name", required = false) Optional<String> name) {
		try {
			if (identification.isPresent()) {
				return ResponseEntity.ok(usuarioService.findByIdentificacao(identification.get()));
			}
			else if (name.isPresent()){
				return ResponseEntity.ok(usuarioService.findByName(name.get()));
			} 
			else {
				return ResponseEntity.ok(usuarioService.findAll());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity getUser(@PathVariable(value="id") long id) {
		try {
			return ResponseEntity.ok(usuarioService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@PostMapping("/")
	public ResponseEntity createDoador(@RequestBody UsuarioDTO usuarioDTO) {
		try {
			return ResponseEntity.ok(usuarioService.createDoador(usuarioDTO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());		}
	}
	
	//remover usuários do sistema localizados pelo seu documento de identificação
	@DeleteMapping("/{identification}")
	public ResponseEntity delete(@PathVariable("identification") String identification) {
		try {
			usuarioService.delete(identification);
			return ResponseEntity.ok("Deletado com sucesso!");
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
			
			UsuarioDTO usuarioDTO = new UsuarioDTO(dadosTriangulo[1], dadosTriangulo[2], dadosTriangulo[3], getClasse(dadosTriangulo[4]), dadosTriangulo[0]);
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
	@PutMapping("/{identificacao}")
	public ResponseEntity put(@PathVariable String identificacao, @RequestBody Usuario usuario) {
		try {
			return ResponseEntity.ok(usuarioService.update(identificacao, usuario.getNome(), usuario.getEmail(), usuario.getCelular()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}

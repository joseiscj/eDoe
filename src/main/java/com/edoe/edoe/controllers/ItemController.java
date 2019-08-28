package com.edoe.edoe.controllers;

import java.util.List;

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

import com.edoe.edoe.dto.ItemDTO;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.services.ItemService;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("item")
public class ItemController {
	
	@Autowired
	ItemService itemService;	
	
	//O sistema deve permitir uma listagem de todos os itens inseridos no sistema ordenada pela quantidade do item no sistema.
	@GetMapping("/donation")
	public ResponseEntity findAllDonationOrdered() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.findAllDonationOrderedByQuantidade());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//Listar todos os itens necessário cadastrados no eDoe.com ordenados pelo identificador único dos itens;
	@GetMapping("/necessary")
	public ResponseEntity findAllNecessaryOrdered() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.findAllNecessaryOrderedByQuantidade());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//Exibir informações de um item já cadastrado através do seu identificador único;
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable(value="id") long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.findById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//O sistema deve listar todos os itens para doacao relacionados a uma dada string de pesquisa.
	@GetMapping("/donation/description")
	public ResponseEntity getByPartialDescription(@RequestBody String string) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.findAllDonationByPartialDescription());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//Cadastrar itens para doação/necessarios
	@PostMapping("/")
	public ResponseEntity create(@RequestBody ItemDTO itemDTO) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.create(itemDTO));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	//receber o id do item a ser apagado
	//Remover itens a serem doados/necessarios
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable(value="id") long id) {
		try {
			itemService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Item deletado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	//Atualizar tags e quantidade de um item a ser doado/necessario
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable(value="id") long id, @RequestBody ItemDTO item) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.update(id, item));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
	@GetMapping("/matches/{id}")
	public ResponseEntity getMatches(@PathVariable(value="id") long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.match(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}

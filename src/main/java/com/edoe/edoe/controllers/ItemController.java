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
@RequestMapping(value="/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;	
	
	//O sistema deve permitir uma listagem de todos os itens inseridos no sistema ordenada pela quantidade do item no sistema.
	@GetMapping("/donation")
	public ResponseEntity<List<Item>> findAllDonationOrdered() {
		try {
			return new ResponseEntity<List<Item>>(itemService.findAllDonationOrderedByQuantidade(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Listar todos os itens necessário cadastrados no eDoe.com ordenados pelo identificador único dos itens;
	@GetMapping("/necessary")
	public ResponseEntity<List<Item>> findAllNecessaryOrdered() {
		try {
			return new ResponseEntity<List<Item>>(itemService.findAllNecessaryOrderedByQuantidade(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Exibir informações de um item já cadastrado através do seu identificador único;
	@GetMapping("/{id}")
	public ResponseEntity<Item> getById(@PathVariable(value="id") long id) {
		try {
			return new ResponseEntity<>(itemService.findById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//O sistema deve listar todos os itens para doacao relacionados a uma dada string de pesquisa.
	@GetMapping("/donation/description")
	public ResponseEntity<List<Item>> getByPartialDescription(@RequestBody String string) {
		try {
			return new ResponseEntity<>(itemService.findAllDonationByPartialDescription(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Cadastrar itens para doação/necessarios
	@PostMapping("/")
	public ResponseEntity<Item> create(@RequestBody ItemDTO itemDTO) {
		try {
			return new ResponseEntity<>(itemService.create(itemDTO), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	//Remover itens a serem doados/necessarios
	@DeleteMapping("/")
	public ResponseEntity<Object> delete(@RequestBody ItemDTO itemDTO) {
		try {
			itemService.delete(itemDTO);
			return new ResponseEntity<Object>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	//Atualizar tags e quantidade de um item a ser doado/necessario
	@PutMapping("/{id}")
	public ResponseEntity<Item> update(@PathVariable(value="id") long id, @RequestBody ObjectNode json) {
		try {
			return new ResponseEntity<>(itemService.update(id, json), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

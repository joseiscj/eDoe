package com.edoe.edoe.controllers;

import java.util.Optional;

import com.edoe.edoe.dto.ItemDTO;
import com.edoe.edoe.models.Status;
import com.edoe.edoe.services.ItemService;
import com.edoe.edoe.util.exceptions.StatusException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
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

@RestController
@RequestMapping("item")
public class ItemController {
	
	@Autowired
	ItemService itemService;	
	
	@GetMapping("/")
	public ResponseEntity get(@RequestParam(value="status", required=false) Optional<Status> status, @RequestParam(value="description", required=false) Optional<Description> description){
		try {
			if (status.isPresent()) {
				if (status.get().equals(Status.DOACAO)) return ResponseEntity.ok(itemService.findAllDonationOrderedByQuantidade());

				else if (status.get().equals(Status.NECESSARIO)) return ResponseEntity.ok(itemService.findAllNecessaryOrderedByQuantidade());

				else throw new StatusException("O status não é válido.");

			} else if (description.isPresent()){
				return ResponseEntity.ok(itemService.findAllDonationByPartialDescription());
			} else {
				return ResponseEntity.ok(itemService.findAll());
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	//Exibir informações de um item já cadastrado através do seu identificador único;
	@GetMapping("/{id}")
	public ResponseEntity getById(@PathVariable("id") String id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.findById(id));
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
	public ResponseEntity delete(@PathVariable(value="id") String id) {
		try {
			itemService.delete(id);
			return ResponseEntity.status(HttpStatus.OK).body("Item deletado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		
	}
	
	//Atualizar tags e quantidade de um item a ser doado/necessario
	@PutMapping("/{id}")
	public ResponseEntity update(@PathVariable(value="id") String id, @RequestBody ItemDTO item) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.update(id, item));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
		}
	}
	
	@GetMapping("/matches/{id}")
	public ResponseEntity getMatches(@PathVariable(value="id") String id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(itemService.match(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}

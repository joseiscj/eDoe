package com.edoe.edoe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edoe.edoe.services.DoacaoService;

@RestController
@RequestMapping("doacao")
public class DoacaoController {
	
	@Autowired
	DoacaoService doacaoService;
	
	@PostMapping("/{itemNecessarioId}/{itemDoacaoId}")
	public ResponseEntity makeDonation(@PathVariable(value="itemNecessarioId") long idNec, @PathVariable(value="itemDoacaoId") long idDoa) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(doacaoService.makeDonation(idNec, idDoa));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	@GetMapping("/")
	public ResponseEntity getDonations() {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(doacaoService.findAll());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

}

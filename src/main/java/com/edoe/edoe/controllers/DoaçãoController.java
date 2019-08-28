package com.edoe.edoe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edoe.edoe.models.Doação;
import com.edoe.edoe.services.DoaçãoService;

@RestController
@RequestMapping("doação")
public class DoaçãoController {
	
	@Autowired
	DoaçãoService doacaoService;
	
	@GetMapping("/")
	public ResponseEntity<List<Doação>> getDonation() {
		return new ResponseEntity<List<Doação>>(doacaoService.findAll(), HttpStatus.OK);
	}

}

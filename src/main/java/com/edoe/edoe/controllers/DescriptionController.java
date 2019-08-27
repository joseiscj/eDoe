package com.edoe.edoe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edoe.edoe.models.Description;
import com.edoe.edoe.services.DescriptionService;

@RestController
@RequestMapping("descricao")
public class DescriptionController {
	
	@Autowired
	DescriptionService descricaoService;	
	
	@GetMapping("/")
	public ResponseEntity<List<Description>> getItens() {
		return new ResponseEntity<List<Description>>(descricaoService.findAll(), HttpStatus.OK);
	}

}

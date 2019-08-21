package com.edoe.edoe.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
@RequestMapping(value="/")
public class ItemController {
	
	@Autowired
	ItemService itemService;	
	
	@GetMapping("/itens")
	public List<Item> getItens() {
		return itemService.findAll();
	}
	
	@GetMapping("/item/{id}")
	public Item getItem(@PathVariable(value="id") long id) {
		return itemService.findById(id);
	}
	
	@PostMapping("/item")
	public Item create(@RequestBody ItemDTO itemDTO) {
		return itemService.create(itemDTO);
	}
	
	@DeleteMapping("/item")
	public void delete(@RequestBody ItemDTO itemDTO) {
		itemService.delete(itemDTO);
	}
	
	@PutMapping("/item")
	public Item put(@RequestBody ItemDTO itemDTO) {
		return itemService.update(itemDTO);
	}

}

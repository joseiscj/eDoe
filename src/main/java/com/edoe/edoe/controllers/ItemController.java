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

import com.edoe.edoe.models.Item;
import com.edoe.edoe.repository.ItemRepository;

@RestController
@RequestMapping(value="/")
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@GetMapping("/itens")
	public List<Item> getItens() {
		return itemRepository.findAll();
	}
	
	@GetMapping("/item/{id}")
	public Item getItem(@PathVariable(value="id") long id) {
		return itemRepository.findById(id);
	}
	
	@PostMapping("/item")
	public Item create(@RequestBody Item item) {
		return itemRepository.save(item);
	}
	
	@DeleteMapping("/item")
	public void delete(@RequestBody Item item) {
		itemRepository.delete(item);
	}
	
	@PutMapping("/item")
	public Item put(@RequestBody Item item) {
		return itemRepository.save(item);
	}

}

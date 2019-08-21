package com.edoe.edoe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.edoe.dto.ItemDTO;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.repository.ItemRepository;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	DescricaoService descricaoService;
	
	public Item create(ItemDTO itemDTO) {
		Item item = itemDTO.getItem();
		descricaoService.create(item.getDescricao());
		return itemRepository.save(item);
	}
	
	public List<Item> findAll(){
		return itemRepository.findAll();
	}
	
	public Item findById(long id) {
		return itemRepository.findById(id);
	}
	
	public void delete(ItemDTO itemDTO) {
		Item item = itemDTO.getItem();
		itemRepository.delete(item);
	}
	
	public Item update(ItemDTO itemDTO) {
		Item item = itemDTO.getItem();
		descricaoService.create(item.getDescricao());
		return itemRepository.save(item);
	}
}

package com.edoe.edoe.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.edoe.dto.ItemDTO;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.models.Status;
import com.edoe.edoe.repository.ItemRepository;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	DescriptionService descriptionService;
	
	public Item create(ItemDTO itemDTO) {
		Item item = itemDTO.getItem();
		descriptionService.create(item.getDescription());
		return itemRepository.save(item);
	}
	
	public List<Item> findAllDonation(){
		return itemRepository.findByStatus(Status.DOACAO);
	}
	
	public Item findById(long id) {
		return itemRepository.findById(id);
	}
	
	public void delete(ItemDTO itemDTO) {
		Item item = itemDTO.getItem();
		itemRepository.delete(item);
	}
	
	//Só deve ser possível atualizar 'tags' e 'quantidade' de um item
	public Item update(long id, ObjectNode json) {
		Item item = itemRepository.findById(id);
		
		if (json.get("tags") != null) {
			item.setTags(json.get("tags").asText());
		}
		
		if (json.get("quantidade") != null) {
			item.setQuantity(json.get("quantidade").asInt());
		}
	
		return itemRepository.save(item);
	}

	public List<Item> findAllDonationOrderedByQuantidade() {
		return itemRepository.findByStatusOrderByQuantityDesc(Status.DOACAO);
	}

	public List<Item> findAllDonationByPartialDescription() {
		// TODO Auto-generated method stub
		return new ArrayList<Item>();
	}

	public List<Item> findAllNecessaryOrderedByQuantidade() {
		return itemRepository.findByStatusOrderByIdAsc(Status.DOACAO);
	}
}

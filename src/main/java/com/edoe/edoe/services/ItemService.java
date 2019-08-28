package com.edoe.edoe.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.edoe.dto.ItemDTO;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.models.Status;
import com.edoe.edoe.models.Usuario;
import com.edoe.edoe.repository.ItemRepository;
import com.edoe.edoe.util.SortByScore;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class ItemService {
	
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	DescriptionService descriptionService;
	
	@Autowired
	UsuarioService userService;
	
	public Item create(ItemDTO itemDTO) {
		Item item = itemDTO.getItem();
		Usuario usuario = userService.findById(itemDTO.getUserId());
		item.setUser(usuario);
		descriptionService.create(item.getDescription());
		return itemRepository.save(item);
	}
	
	public List<Item> findAllDonation(){
		return itemRepository.findAllByStatus(Status.DOACAO);
	}
	
	public Item findById(long id) {
		return itemRepository.findById(id);
	}
	
	public void delete(long id) {
		itemRepository.deleteById(id);
	}
	
	//Só deve ser possível atualizar 'tags' e 'quantidade' de um item
	public Item update(long id, ItemDTO itemDTO) {
		Item item = itemRepository.findById(id);
		System.out.println(item);
		if (itemDTO.getQuantity() > 0) {
			item.setQuantity(itemDTO.getQuantity());
		}
		
		if (itemDTO.getTags() != null) {
			item.setTags(itemDTO.getTags());
		}
	
		return itemRepository.save(item);
	}

	public List<Item> findAllDonationOrderedByQuantidade() {
		return itemRepository.findAllByStatusOrderByQuantityDesc(Status.DOACAO);
	}

	public List<Item> findAllDonationByPartialDescription() {
		// TODO Auto-generated method stub
		return new ArrayList<Item>();
	}

	public List<Item> findAllNecessaryOrderedByQuantidade() {
		return itemRepository.findAllByStatusOrderByIdAsc(Status.DOACAO);
	}
	
	public List<Item> match(long id) {
		Item itemNecessario = itemRepository.findById(id);
		List<Item> list = itemRepository.findAllByDescriptionAndStatus(itemNecessario.getDescription(), Status.DOACAO);
		list.remove(itemNecessario);
		for (Item item : list) {
			tagScore(item, itemNecessario);
		}
		Collections.sort(list, new SortByScore());
		return list;
	}
	
	
	//Calcula o score de cada item doado
	private void tagScore(Item itemDoado, Item necessaryItem) {
		String[] doadoTags = itemDoado.getTags().split(",");
		String[] necessaryTags = necessaryItem.getTags().split(",");
		
		int score = 0;
		for (int i = 0; i < doadoTags.length; i++) {
			for (int j = 0; j < necessaryTags.length; j++) {
				if (doadoTags[i].equals(necessaryTags[j])) {
					if (i == j) itemDoado.setMatchScore(score += 10);
					else itemDoado.setMatchScore(score += 5);
				}
			}
		}
	}
}

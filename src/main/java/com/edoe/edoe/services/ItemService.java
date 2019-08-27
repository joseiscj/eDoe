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
	
	public List<String> matches(Item[] itensDoados, String identificationReceptor, Item[] necessariesItens) {
		ArrayList<String> list = new ArrayList<String>();
		for (int i = 0; i < itensDoados.length; i++) {
			for (int j = 0; j < necessariesItens.length; j++) {
				if (itensDoados[i].getDescription().getDescription()
						.equalsIgnoreCase(necessariesItens[j].getDescription().getDescription())) {
					tagScore(itensDoados[i], necessariesItens[j]);
				}
			}
			
		}
		sort(itensDoados);
		for (Item item : itensDoados) {
			if (item.getMatchScore() > 0) {
				list.add(item.getId() + " - " + item.getDescription().getDescription() + 
						", tags: " + item.getTags() + ", quantidade: " + item.getQuantity() +
						", doador: " + item.getUser() + "/" + item.getUser().getIdentificacao());
			}
		}
		return list;
	}
	
	//Utilizando o bubble sort para ordenar de forma descrcescente pelo match score
	private void sort(Item[] itensDoados) {
		for (int i = 1; i < itensDoados.length; i++) {
		    for (int j = 0; j < i; j++) {
		        if (itensDoados[i].getMatchScore() > itensDoados[j].getMatchScore()) {
		            Item temp = itensDoados[i];
		            itensDoados[i] = itensDoados[j];
		            itensDoados[j] = temp;
		        }
		    }
		}
	}
	
	//Calcula o score de cada item doado
	private void tagScore(Item itemDoado, Item necessaryItem) {
		String[] doadoTags = itemDoado.getTags().split(",");
		String[] necessaryTags = necessaryItem.getTags().split(",");
		
		int score = itemDoado.getMatchScore();
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

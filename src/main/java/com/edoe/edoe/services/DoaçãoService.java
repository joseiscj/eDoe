package com.edoe.edoe.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.edoe.models.Doação;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.repository.DoaçãoRepository;

@Service
public class DoaçãoService {
	
	@Autowired
	DoaçãoRepository doacaoRepository;
	
	@Autowired
	ItemService itemService;
	
	public Doação makeDonation(long necessaryID, long donatedID) {
		Item necessaryItem = itemService.findById(necessaryID);
		Item donatedItem = itemService.findById(donatedID);
		
		if (necessaryItem.getDescription().getDescription().equals(donatedItem)) {
			Doação doação = new Doação(donatedItem, necessaryItem);
			donatedItem.setQuantity(donatedItem.getQuantity() - 1);
			necessaryItem.setQuantity(necessaryItem.getQuantity() + 1);
			return doacaoRepository.save(doação);
		}
		return null;
	}

}

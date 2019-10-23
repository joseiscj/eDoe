package com.edoe.edoe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.edoe.edoe.models.Description;
import com.edoe.edoe.repository.DescriptionRepository;

@Service
public class DescriptionService {
	
	@Autowired
	private DescriptionRepository descriptionRepository;
	
	public Description create(Description description) {
		Description descr = descriptionRepository.findByDescription(description.getDescription());
		
		if (descr != null) {
			description.setId(descr.getId());
		}
		
		return descriptionRepository.save(description);
	}
	
	@Cacheable(cacheNames = "Descriptions", key="#descriptions.findAll")
	public List<Description> findAll() {
		return descriptionRepository.findAll();
	}
	

}

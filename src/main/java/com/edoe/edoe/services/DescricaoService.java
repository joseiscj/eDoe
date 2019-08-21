package com.edoe.edoe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edoe.edoe.models.Descricao;
import com.edoe.edoe.repository.DescricaoRepository;

@Service
public class DescricaoService {
	
	@Autowired
	private DescricaoRepository descricaoRepository;
	
	public Descricao create(Descricao descricao) {
		Descricao descr = descricaoRepository.findByDescricao(descricao.getDescricao());
		
		if (descr != null) {
			descricao.setId(descr.getId());
		}
		
		return descricaoRepository.save(descricao);
	}
	
	public List<Descricao> findAll() {
		return descricaoRepository.findAll();
	}
	

}

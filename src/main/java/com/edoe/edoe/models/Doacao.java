package com.edoe.edoe.models;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "doacoes")
public class Doacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private Usuario usuarioDoador;
	
	private Usuario usuarioReceptor;	
	
	private Description descricao;
	
	private int quantity;
	
	public Doacao() {
		
	}

	public Doacao(Usuario usuarioDoador, Usuario usuarioReceptor, Description descricao, int quantity) {
		this.usuarioDoador = usuarioDoador;
		this.usuarioReceptor = usuarioReceptor;
		this.quantity = quantity;
		this.descricao = descricao;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Usuario getUsuarioDoador() {
		return usuarioDoador;
	}

	public void setUsuarioDoador(Usuario usuarioDoador) {
		this.usuarioDoador = usuarioDoador;
	}

	public Usuario getUsuarioReceptor() {
		return usuarioReceptor;
	}

	public void setUsuarioReceptor(Usuario usuarioReceptor) {
		this.usuarioReceptor = usuarioReceptor;
	}

	public Description getDescricao() {
		return descricao;
	}

	public void setDescricao(Description descricao) {
		this.descricao = descricao;
	}


}

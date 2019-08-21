package com.edoe.edoe.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Descricao descricao;
	
	@Column(nullable = false)
	private int quantidade;
	
	private String tags;	

	@ManyToOne
	private Usuario usuario;
	
	private Status status;
	
	//CONSTRUCTOR
	
	public Item() {
		
	}

	public Item(long id, Descricao descricao, int quantidade, String tags, Status status, Usuario usuario) {
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.tags = tags;
		this.status = status;
		this.usuario = usuario;
	}
	
	// GETTERS & SETTERS

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Descricao getDescricao() {
		return descricao;
	}

	public void setDescricao(Descricao descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
}

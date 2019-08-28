package com.edoe.edoe.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//TODO
	///Traduzir para inglês
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Description description;
	
	@Column(nullable = false)
	private int quantity;
	
	private String tags;	

	@ManyToOne
	private Usuario user;
	
	private Status status;
	
	@Transient
	private int matchScore;
	
	//CONSTRUCTOR
	
	public Item() {
		
	}

	public Item(long id, Description descricao, int quantidade, String tags, Status status, Usuario usuario) {
		this.id = id;
		this.description = descricao;
		this.quantity = quantidade;
		this.tags = tags;
		this.status = status;
		this.user = usuario;
		this.matchScore = 0;
	}

	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Description getDescription() {
		return description;
	}

	public void setDescription(Description description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getMatchScore() {
		return matchScore;
	}
	
	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}

	
	// GETTERS & SETTERS
	
	
}

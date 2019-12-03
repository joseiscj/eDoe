package com.edoe.edoe.dto;

import com.edoe.edoe.models.Description;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.models.Status;
import com.edoe.edoe.models.Usuario;

public class ItemDTO {
	private String id;
	
	private Description description;
	
	private int quantity;
	
	private String tags;
	
	private Usuario user;
	
	private String userId;
	
	private Status status;
	
	public ItemDTO(String id, Description descricao, int quantidade, String tags, String usuarioId, Status status) {
		this.id = id;
		this.description = descricao;
		this.tags = tags;
		this.status = status;
		this.user = new Usuario(usuarioId);
		this.quantity = quantidade;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Item getItem() {
		return new Item(this.id, this.description, this.quantity, this.tags, this.status, this.user);
	}

}

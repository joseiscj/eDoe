package com.edoe.edoe.dto;

import com.edoe.edoe.models.Descricao;
import com.edoe.edoe.models.Item;
import com.edoe.edoe.models.Status;
import com.edoe.edoe.models.Usuario;

public class ItemDTO {
	private long id;
	
	private Descricao descricao;
	
	private int quantidade;
	
	private String tags;
	
	private Usuario usuario;
	
	private long usuarioId;
	
	private Status status;
	
	public ItemDTO(long id, Descricao descricao, int quantidade, String tags, long usuarioId, Status status) {
		this.id = id;
		this.descricao = descricao;
		this.tags = tags;
		this.status = status;
		this.usuario = new Usuario(usuarioId);
		this.quantidade = quantidade;
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

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
	
	public Item getItem() {
		return new Item(this.id, this.descricao, this.quantidade, this.tags, this.status, this.usuario);
	}

}

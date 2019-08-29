package com.edoe.edoe.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Doacao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Usuario usuarioDoador;
	
	@ManyToOne
	private Usuario usuarioReceptor;	
	
	@ManyToOne
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
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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

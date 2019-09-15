package com.edoe.edoe.dto;

import com.edoe.edoe.models.Classe;
import com.edoe.edoe.models.Tipo;
import com.edoe.edoe.models.Usuario;

public class UsuarioDTO {
	
	private long id;
	
	private String nome;
	
	private String email;
	
	private String celular;
	
	private Classe classe;
	
	private String identificacao;
	
	private Tipo tipo;

	private String username;

	private String password;
	
	public UsuarioDTO() {
		
	}

	public UsuarioDTO(long id, String nome, String email, String celular, Classe classe, String identificacao,
			Tipo tipo) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.identificacao = identificacao;
		this.tipo = tipo;
	}

	public UsuarioDTO(String nome, String email, String celular, Classe classe, String identificacao) {
		this.nome = nome;
		this.email = email;
		this.celular = celular;
		this.classe = classe;
		this.identificacao = identificacao;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Classe getClasse() {
		return classe;
	}


	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
	}

	public Tipo getTipo() {
		return tipo;
	}
	
	public void setClasse(Classe classe) {
		this.classe = classe;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Usuario getUsuario() {
		return new Usuario(this.id, this.nome, this.email, this.celular, this.classe, this.identificacao,
				this.tipo);
	}
	
	
}

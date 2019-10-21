package com.edoe.edoe.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edoe.edoe.models.Usuario;
import com.edoe.edoe.services.UsuarioService;

public class LoginController {

	@Autowired
	UsuarioService usuarioService;

	@RequestMapping("efetuaLogin")
	public ResponseEntity efetuaLogin(Usuario usuario, HttpSession session) {
		try {
			long id = usuario.getId();
			if (usuarioService.findById(id) != null) {
				session.setAttribute("usuarioLogado", usuario);
				return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(id));
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
		return null;

	}
}
		


package com.example.aplicacion.service;
import java.util.List;

import com.example.aplicacion.entity.Usuario;
public interface IUsuarioService {
	
	public List<Usuario> findAllUsuarios();
	
	public Usuario checkUsuarioLogin(Usuario usuario);

}

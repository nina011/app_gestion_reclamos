package com.example.aplicacion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.aplicacion.dao.IUsuarioDao;
import com.example.aplicacion.entity.Usuario;

@Service("usuarioService")
public class UsuarioServiceImplementacion implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;
	
	
	@Override
	public List<Usuario> findAllUsuarios() {
		return (List<Usuario>) usuarioDao.findAll();
	}


	@Override
	public Usuario checkUsuarioLogin(Usuario usuario) {
		return (Usuario)usuarioDao.findByNombreAndPass(usuario.getNombre(), usuario.getPass());
	}

}

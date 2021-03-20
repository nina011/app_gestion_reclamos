package com.example.aplicacion.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.aplicacion.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario,Long> {

	public Usuario findByNombreAndPass(String nombre, String password);
	
}

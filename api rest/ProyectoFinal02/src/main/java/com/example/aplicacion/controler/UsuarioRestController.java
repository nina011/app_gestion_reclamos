package com.example.aplicacion.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.aplicacion.entity.Reclamo;
import com.example.aplicacion.entity.Usuario;
import com.example.aplicacion.service.UsuarioServiceImplementacion;

@RestController
public class UsuarioRestController {

	@Autowired
	UsuarioServiceImplementacion usuarioService;
	
	@RequestMapping(value="/todos_usuarios", method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> listAllUsuarios(){
		List<Usuario> usuarios = usuarioService.findAllUsuarios();
		if(usuarios.isEmpty()){
			return new ResponseEntity<List<Usuario>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
	
	

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/login")
	public ResponseEntity<?> loginUsuario(@RequestBody Usuario usuario){
		

	Usuario usuarioDb = usuarioService.checkUsuarioLogin(usuario);
	if(usuarioDb !=null) {
		return new ResponseEntity<>(usuarioDb, HttpStatus.OK);		
	}else {
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	}
	/*
	 * 
	 * public ResponseEntity<?>findReclamo(@RequestBody Reclamo reclamo){
		Reclamo reclamoBD = reclamoService.findReclamoPedido(reclamo); //aquí busca en interface service la implementación del metodo findreclamopedido
		if(reclamoBD != null) {
			return new ResponseEntity<>(reclamoBD, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	 * 
	 * 
	 * */
	
}

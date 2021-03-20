package com.example.aplicacion.controler;

import java.util.Date;
import java.util.List;

import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.aplicacion.entity.Reclamo;
import com.example.aplicacion.service.IReclamoService;

@RestController
@RequestMapping("/api")
public class ReclamoRestController {

	@Autowired
	private IReclamoService reclamoService;
	
	//metodo find all
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/reclamos")
	@ResponseStatus(HttpStatus.OK)
	public List<Reclamo> getReclamos(){
		return reclamoService.findAll();
	}
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/ingresar_reclamo")
	public ResponseEntity<Void> addReclamo(@RequestBody Reclamo reclamo){
		if(reclamoService.findReclamoPedido(reclamo)==null) {
			reclamoService.save(reclamo);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/buscar_reclamo_p")
	public ResponseEntity<?>findReclamo(@RequestBody Reclamo reclamo){
		Reclamo reclamoBD = reclamoService.findReclamoPedido(reclamo); //aquí busca en interface service la implementación del metodo findreclamopedido
		if(reclamoBD != null) {
			return new ResponseEntity<>(reclamoBD, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/buscar_reclamo_r")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> findReclamoR(@RequestBody Reclamo reclamo) {
		List<Reclamo> ListaReclamos = reclamoService.findAllbyRut(reclamo);
		if (ListaReclamos != null) {
			if (ListaReclamos.size() != 0) {
				return new ResponseEntity<>(ListaReclamos, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

	}
		/*encontrar diferencias y errores entre los dos métodos, este no devuelve nada y da dos ok. 
		 public ResponseEntity<?>findAllPorFecha(@RequestBody Reclamo reclamo){
		Reclamo  ReclamosBD = new Reclamo();
		
		List<Reclamo> listaReclamos = reclamoService.findAllByFecha(ReclamosBD);
		if(listaReclamos.size()!=0) { 
		 */
		/*Reclamo reclamoBD = new Reclamo();
		
		List<Reclamo>ListaReclamos = reclamoService.findAllbyRut(reclamoBD);
		if(ListaReclamos.size()!=0) {
			return new ResponseEntity<>(ListaReclamos,HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}*/
	
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/buscar_reclamo_fecha")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> findReclamoByFecha(@RequestBody Reclamo reclamo){		
		List<Reclamo> ListaReclamos = reclamoService.findByFecha(reclamo);
		if (ListaReclamos != null) {
			if (ListaReclamos.size() != 0) {
				return new ResponseEntity<>(ListaReclamos, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/buscar_reclamo_estado")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> findByEstado(@RequestBody Reclamo reclamo){		
		List<Reclamo> ListaReclamos = reclamoService.findByEstado(reclamo);
		if (ListaReclamos != null) {
			if (ListaReclamos.size() != 0) {
				return new ResponseEntity<>(ListaReclamos, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PutMapping("/update/{id}")
	public ResponseEntity<?>updateReclamo(@PathVariable (value="id") Long id,@RequestBody Reclamo reclamo){
		Reclamo reclamoBD = null;
		reclamoBD = reclamoService.findById(id);
		if(reclamoBD != null) {
			reclamoBD.setEstado(reclamo.getEstado());
			reclamoService.updateReclamo(reclamoBD);
			return new ResponseEntity<>(reclamoBD, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
			
	
	}
	
	@CrossOrigin(origins = "http://127.0.0.1:5500")
	@PostMapping("/buscar_reclamo_id/{id}")
	public ResponseEntity<?>findById(@PathVariable (value = "id")Long id,@RequestBody Reclamo reclamo){
		Reclamo reclamoBD = reclamoService.findById(id); 
		if(reclamoBD != null) {
			return new ResponseEntity<>(reclamoBD, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	
	
	
		
	/*@CrossOrigin(origins = "http://127.0.0.1:5500")
	@GetMapping("/activo")
	@ResponseStatus(HttpStatus.OK)
	public List<Reclamo> getReclamos(){
		return reclamoService.findAll();
	}
*/
/*	@PutMapping("/update_estado")
	public ResponseEntity<?>updateUsuarioEstado(@RequestBody Reclamo reclamo){
		Reclamo reclamoBd = null;
		reclamoBd = reclamoService.
		
	}
	
	
	
	/*@CrossOrigin(origins = "http://127.0.0.1:5500")
	  @PostMapping("/buscar_fecha_entre")
	  public List<Reclamo>findReclamoBetween(@RequestBody Reclamo reclamo){
		  return reclamoService.findAllByFecha(reclamo);
	  }*/
/*	  @PostMapping("/buscar_fecha_entre")
	public ResponseEntity<?>findAllPorFecha(@RequestBody Reclamo reclamo){
		Reclamo  ReclamosBD = new Reclamo();
		
		List<Reclamo> listaReclamos = reclamoService.findAllByFecha(ReclamosBD);
		if(listaReclamos.size()!=0) {
			return new ResponseEntity<>(listaReclamos, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
*/
	
/*con path variables:
 * 	@PostMapping("/buscar_fecha_entre/{f1}/{f2}")
	public ResponseEntity<?>findAllPorFecha(@PathVariable(value="f1") Date f1,@PathVariable(value="f2") Date f2,@RequestBody Reclamo reclamo){
		List<Reclamo> listaReclamos = reclamoService.findAllByFecha(f1,f2);
		if(listaReclamos.size()!=0) {
			return new ResponseEntity<>(listaReclamos, HttpStatus.OK);
		}else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	}
	@PostMapping("buscar_fecha_entre")
	public List<Reclamo>findReclamoBetween(@RequestBody Reclamo reclamo){
		return reclamoService.findAllByFecha(reclamo);
	}
	
	*/
	/*@PostMapping("buscar_fecha_b")
	public List<Reclamo>findReclamoFB(@RequestBody Reclamo reclamo1, Reclamo reclamo2){
		return reclamoService.findByDatesBetween(reclamo1, reclamo2);
	}
	*/
	
	
}

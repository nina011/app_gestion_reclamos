package com.example.aplicacion.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.example.aplicacion.entity.Reclamo;


public interface IReclamoService {
	
	public List<Reclamo> findAll();
	
	public Reclamo findReclamo(Reclamo reclamo);
	
		
	public Optional<Reclamo> findReclamoById(Long Reclamo_id);
	

	public Reclamo findReclamoPedido(Reclamo reclamo);
	
	public Reclamo findById(Long id);
	
	public Reclamo findByIdSQL(Long id);
	
	public void save(Reclamo reclamo);
	
    public List<Reclamo> findAllbyRut(Reclamo reclamo);
    
    public List<Reclamo> findByEstado(Reclamo reclamo);

	public List<Reclamo> findByFecha(Reclamo reclamo);
  
    public Reclamo updateReclamo(Reclamo reclamo);
   // public List<Reclamo>findAllByFecha(Reclamo reclamo);
    
    
  
	// public List<Reclamo>findAllByFecha(Date f1, Date f2); con path variable
    //public List<Reclamo>findAllByFecha(Date startDate, Date endDate);
    
	//public List<Reclamo>findAllByRut(Reclamo reclamo);
	
	//metodo para buscar fechas
	//public Reclamo findBetweenDates();
}

package com.example.aplicacion.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.aplicacion.dao.IReclamoDao;
import com.example.aplicacion.entity.Reclamo;

@Service
public class ReclamoServiceImplementacion implements IReclamoService {
	
	@Autowired
	private IReclamoDao reclamoDao; 

	@Override
	@Transactional(readOnly = true)
	public List<Reclamo> findAll() {
		return (List<Reclamo>) reclamoDao.findAll();
	}
	
	

	@Override
	@Transactional(readOnly = true)
	public Reclamo findReclamo(Reclamo reclamo) {
		return reclamoDao.findByRut(reclamo.getRut());
		//return (Reclamo) reclamoDao.findByRut(reclamo.getRut());
		
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Reclamo> findReclamoById(Long Reclamo_id) {
		return (Optional<Reclamo>)reclamoDao.findById(Reclamo_id);
	}

	@Override
	@Transactional(readOnly = true)
	public Reclamo findById(Long id) {
		return reclamoDao.findById(id).orElse(null);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Reclamo findByIdSQL(Long id) {
		return reclamoDao.findByIdSQL(id);
	}

	@Override
	public void save(Reclamo reclamo) {
		reclamoDao.save(reclamo);
		
	}


	@Override
	@Transactional(readOnly = true)
	public Reclamo findReclamoPedido(Reclamo reclamo) {
		return reclamoDao.findByPedido(reclamo.getPedido());
	}


	
	@Override
	@Transactional(readOnly = true)
	public List<Reclamo> findAllbyRut(Reclamo reclamo) {
		return (List<Reclamo>)reclamoDao.findAllByRut(reclamo.getRut());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Reclamo> findByFecha(Reclamo reclamo) {
		return (List<Reclamo>)reclamoDao.findByFecha(reclamo.getFecha());
	}


	@Override
	@Transactional(readOnly = true)
	public List<Reclamo> findByEstado(Reclamo reclamo) {
		return (List<Reclamo>)reclamoDao.findByEstado(reclamo.getEstado());
	}



	@Override
	public Reclamo updateReclamo(Reclamo reclamo) {
		return (Reclamo)reclamoDao.save(reclamo);
	}



	




	/*@Override
	public List<Reclamo> findAllByFecha(Reclamo reclamo) {
		return(List<Reclamo>)reclamoDao.findAllByFecha(reclamo.getFecha(), reclamo.getFecha());
	}
    */



	

/*		@Override
	public List<Reclamo> findAllByFecha(Date startDate, Date endDate) {
		return (List<Reclamo>)reclamoDao.findAllByFecha(startDate, endDate);
	}

 * @Override
	public List<Reclamo> findAllByFecha(Reclamo reclamo) {
		return (List<Reclamo>)reclamoDao.findAllByFecha(reclamo.getFecha(), reclamo.getFecha());

	
	}
 * con path variable:
		@Override
	public List<Reclamo> findAllByFecha(Date f1, Date f2) {
		return (List<Reclamo>)reclamoDao.findAllByFecha(f1,f2);
	}

	}*/







	/*
	 * 
	 * @Override
	public List<Reclamo> findByFechaBetween(Reclamo reclamo1,Reclamo reclamo2) {
		return (List<Reclamo>)reclamoDao.findByFechaBetween(reclamo1.getFecha(), reclamo2.getFecha());
	}
*/


	

/*	@Override
	@Transactional(readOnly = true)
	public Reclamo findByPedido(Reclamo reclamo) {
		return reclamoDao.findByPedido(reclamo.getPedido());
	}*/
//return reclamoDao.findById(id).orElse(null);

	

	/*@Override
	public Reclamo findBetweenDates() {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
	
}

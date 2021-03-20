package com.example.aplicacion.dao;


import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.example.aplicacion.entity.Reclamo;


public interface IReclamoDao extends CrudRepository<Reclamo,Long> {

	public Reclamo findByRut(String rut);
	
	public Reclamo findByPedido(String pedido);
	
	public Optional<Reclamo>findById(Long id);
	
	public List<Reclamo>findAllByRut(String rut);
	
	@Query("select r from Reclamo r where r.id=?1")
	public Reclamo findByIdSQL(Long id);
	
	@Query("select r from Reclamo r where r.fecha=?1")
	public List<Reclamo> findByFecha(String fecha);
	 
	@Query("select r from Reclamo r where r.estado=?1")
	public List<Reclamo> findByEstado(String estado);
	 
	/*
	
	 
	 @Query(value = "SELECT * FROM PAYMENT_MASTER WHERE LAST_UPDATED >= :startDate AND LAST_UPDATED <= :endDate", nativeQuery = true)
List<PaymentMaster> getAllBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
	 
	 * SELECT e FROM Events e WHERE e.eventsDate BETWEEN :startDate AND :endDate
	 * 
	 *//*
	@Query("select r from Reclamo r where r.from between ?1 and ?2 and r.to between ?1 and ?2")
	public List<Reclamo> findByFechaBetween(Date inicio, Date termino);
	*/
	/*
	IMPORTANTE!!! FINDBY*VALOR* DE LA CLASE ENTITY SINO NO FUNCIONA
	@Query("select b from Book b " +
         "where b.from between ?1 and ?2 and b.to between ?1 and ?2")
  List<Book> findByDatesBetween(Date departure, Date arrival);
	 
	@Query("select r from Reclamo r where r.createdDate >= : from and r.createdDate <= :to")
	List<Reclamo> findByCreatedDateBetween(@Param("from") Date from, @Param("to") Date to);
	
	 @Query("select r from Reclamo r where r.fecha between ?1 and ?2")
	 public List<Reclamo>findAllByFecha(Date startDate, Date endDate);*/
	/*	
	 * query 2: @Query("select r from Reclamo r where r.fecha between ?1 and ?2")
	 *  @Query("select r from Reclamo r where r.fecha between :startDate and :endDate")
	 public List<Reclamo>findAllByFecha(Date startDate, Date endDate);
	*
	* otra query:
	* @Query("select r from Recla r where r")
	*/
	
	
	
}

package com.example.aplicacion.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.TemporalType;
import javax.persistence.Temporal;



@Entity
@Table(name="reclamos")
public class Reclamo implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nombre_r", length=15)
	private String nombre;
	
	@Column(name="apellido_r", length=15)
	private String apellido;
	
	@Column(name="email_r",length = 60)
	private String email;
	
	@Column(name="rut_r", length=12)
	private String rut;
	
	@Column(name="telefono_r", length=12)
	private String telefono;
	
	@Column(name="tipo_reclamo_r", length=50)
	private String tipo_reclamo;
	
	@Column(name="descripcion_r", length=500)
	private String descripcion;
	

	@Column(name="n_pedido_r", length=20, unique = true)
	private String pedido;
	
	@Column(name="fecha_r",length=10)
	private String fecha;
	
	@Column(name="estado",length=1)
	private String estado = "1";
	
	/*@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
	*/
	
	/*@Column(name="activo")
	private Boolean activo = true;
	*/
	
	
	/*metodo para crear la fecha
	@PrePersist
	public void prePersist() {
		fecha = new Date(); 
	}
	*/
	
	
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombre() {
		return nombre;
	}
	public String getPedido() {
		return pedido;
	}
	public void setPedido(String pedido) {
		this.pedido = pedido;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTipo_reclamo() {
		return tipo_reclamo;
	}
	public void setTipo_reclamo(String tipo_reclamo) {
		this.tipo_reclamo = tipo_reclamo;
	}
	

	private static final long serialVersionUID = 1L;
}

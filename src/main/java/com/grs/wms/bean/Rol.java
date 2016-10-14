package com.grs.wms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class Rol {
	@Id
	@Column(name="id_rol")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_rol;
	@Column
	private String nombre;
	@Column
	private String descripcion;
	public Integer getIdRol() {
		return id_rol;
	}
	public void setIdRol(Integer idRol) {
		this.id_rol = idRol;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Rol(Integer idRol, String nombre, String descripcion) {
		super();
		this.id_rol = idRol;
		this.nombre = nombre;
		this.descripcion = descripcion;
	}
	public Rol() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

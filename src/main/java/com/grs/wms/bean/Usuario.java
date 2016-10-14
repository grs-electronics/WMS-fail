package com.grs.wms.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Usuario")
public class Usuario {
	@Id
	@Column(name="id_usuario")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idUsuario;
	@Column
	private String nombre;
	@Column
	private String nick;
	@Column
	private String contrasena;
	@Column
	private String correo;
	@ManyToOne
	private Rol rol;
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Rol getRol() {
		return rol;
	}
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Usuario(Integer idUsuario, String nombre, String nick, String contrasena, String correo, Rol rol) {
		super();
		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.nick = nick;
		this.contrasena = contrasena;
		this.correo = correo;
		this.rol = rol;
	}
	
}

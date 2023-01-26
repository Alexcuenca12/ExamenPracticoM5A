package com.ista.springboot.web.examen.app.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "empleado")
@Getter
@Setter
public class Empleado implements Serializable {

	private static final long serialVersionUID = -8743356424363980633L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_empleado;

	@Column(length = 45)
	@NotNull(message = "No ingresar un valor null")
	@NotEmpty(message = "Campo Vacio")
	private String apellido;
	
	@Column(length = 45)
	@NotNull(message = "No ingresar un valor null")
	@NotEmpty(message = "Campo Vacio")
	private String nombre;
	
	@Column(length = 15)
	@NotNull(message = "No ingresar un valor null")
	@NotEmpty(message = "Campo Vacio")
	private String telefono;
	
	@Column(length = 45)
	@NotNull(message = "No ingresar un valor null")
	@NotEmpty(message = "Campo Vacio")
	private String direccion;
	
	@NotNull(message = "No ingresar un valor null")
	@Column(name = "Fecha_N")
	@Temporal(TemporalType.DATE)
	private Date fecha_nacimiento;
	
	@Column(length = 45)
	@NotNull(message = "No ingresar un valor null")
	@NotEmpty(message = "Campo Vacio")
	private String observacion;
	
	@NotNull(message = "No ingresar un valor null")
	private int dias_trabajo;
	
	@Positive
	@DecimalMin(value = "15")
	@NotNull(message = "No ingresar un valor null")
	private Double sueldo;

}

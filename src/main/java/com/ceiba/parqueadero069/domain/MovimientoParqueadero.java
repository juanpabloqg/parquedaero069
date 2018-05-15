package com.ceiba.parqueadero069.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MovimientoParqueadero {


	private Integer id;
	
	private Vehiculo vehiculo;

	private LocalDateTime fechaIngreso;

	private LocalDateTime fechaRetiro;
	
	private BigDecimal valorCobrado;
	
	private String estado;

	public MovimientoParqueadero() {
		
	}

	public MovimientoParqueadero(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaRetiro,
			BigDecimal valorCobrado, String estado) {
		
		this.vehiculo = vehiculo;
		this.fechaIngreso = fechaIngreso;
		this.fechaRetiro = fechaRetiro;
		this.valorCobrado = valorCobrado;
		this.estado = estado;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public LocalDateTime getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDateTime fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDateTime getFechaRetiro() {
		return fechaRetiro;
	}

	public void setFechaRetiro(LocalDateTime fechaRetiro) {
		this.fechaRetiro = fechaRetiro;
	}

	public BigDecimal getValorCobrado() {
		return valorCobrado;
	}

	public void setValorCobrado(BigDecimal valorCobrado) {
		this.valorCobrado = valorCobrado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}

package com.ceiba.parqueadero069.domain;

import java.time.LocalDateTime;
import java.util.Date;

public class Parqueo {

	private static final Integer VALOR_CARRO_HORA = 1000;

	private static final Integer VALOR_MOTO_HORA = 500;

	private static final Integer VALOR_DIA_CARRO = 8000;

	private static final Integer VALOR_DIA_MOTO = 4000;

	private static final Integer CAPACIDAD_MAXIMA_CARRO = 20;

	private static final Integer CAPACIDAD_MAXIMA_MOTOS = 10;

	private Vehiculo vehiculoModel;

	private LocalDateTime fechaIngreso;

	private LocalDateTime fechaRetiro;

	public Parqueo() {
		
	}

	public Parqueo(Vehiculo vehiculoModel, LocalDateTime fechaIngreso, LocalDateTime fechaRetiro) {

		this.vehiculoModel = vehiculoModel;
		this.fechaIngreso = fechaIngreso;
		this.fechaRetiro = fechaRetiro;
	}

	public Vehiculo getVehiculoModel() {
		return vehiculoModel;
	}

	public void setVehiculoModel(Vehiculo vehiculoModel) {
		this.vehiculoModel = vehiculoModel;
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
	
	

}

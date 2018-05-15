package com.ceiba.parqueadero069.testDataBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;

public class MovimientoParqueaderoTestDataBuilder {

	private Vehiculo vehiculo;

	private LocalDateTime fechaIngreso;

	private LocalDateTime fechaRetiro;
	
	private BigDecimal valorCobrado;
	
	private String estado;

	public MovimientoParqueaderoTestDataBuilder() {

		this.vehiculo = VehiculoTestDataBuilder.aVehiculoTestDataBuilder();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		this.fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO, dateTimeFormatter);

//		this.fechaRetiro = LocalDateTime.parse("2018-05-08 10:00:00" , dateTimeFormatter);
		
		this.estado = MovimientoParqueaderoConstant.ESTADO_INGRESADO;
	}

	public MovimientoParqueaderoTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {

		this.fechaIngreso = fechaIngreso;
		return this;

	}

	public MovimientoParqueaderoTestDataBuilder withFechaRetiro(LocalDateTime fechaRetiro) {

		this.fechaRetiro = fechaRetiro;
		return this;

	}

	public MovimientoParqueaderoTestDataBuilder withVehiculo(Vehiculo vehiculo) {

		this.vehiculo = vehiculo;
		return this;

	}
	
	public MovimientoParqueaderoTestDataBuilder withValorCobrado(BigDecimal valorCobrado) {

		this.valorCobrado = valorCobrado;
		return this;

	}
	
	public MovimientoParqueaderoTestDataBuilder withEstado(String estado) {

		this.estado = estado;
		return this;

	}
	
	public MovimientoParqueadero build() {
		
		return new MovimientoParqueadero(this.vehiculo, this.fechaIngreso, this.fechaRetiro, this.valorCobrado, this.estado);
	}

}

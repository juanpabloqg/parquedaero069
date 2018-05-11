package com.ceiba.parqueadero069.testDataBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;

public class MovimientoParqueaderoTestDataBuilder {

	private Vehiculo vehiculo;

	private LocalDateTime fechaIngreso;

	private LocalDateTime fechaRetiro;

	public MovimientoParqueaderoTestDataBuilder() {

		this.vehiculo = VehiculoTestDataBuilder.aVehiculoTestDataBuilder();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		this.fechaIngreso = LocalDateTime.parse("2018-05-08 08:00:00", dateTimeFormatter);

		this.fechaRetiro = LocalDateTime.parse("2018-05-08 10:00:00" , dateTimeFormatter);
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
	
	public MovimientoParqueadero build() {
		
		return new MovimientoParqueadero(this.vehiculo, this.fechaIngreso, this.fechaRetiro);
	}

}

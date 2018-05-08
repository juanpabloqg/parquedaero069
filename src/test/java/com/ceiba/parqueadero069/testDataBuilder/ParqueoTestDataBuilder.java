package com.ceiba.parqueadero069.testDataBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.ceiba.parqueadero069.model.Parqueo;
import com.ceiba.parqueadero069.model.Vehiculo;

public class ParqueoTestDataBuilder {

	private Vehiculo vehiculo;

	private LocalDateTime fechaIngreso;

	private LocalDateTime fechaRetiro;

	public ParqueoTestDataBuilder() {

		this.vehiculo = VehiculoTestDataBuilder.aVehiculoTestDataBuilder();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		this.fechaIngreso = LocalDateTime.parse("2018-05-08 08:00:00", dateTimeFormatter);

		this.fechaRetiro = LocalDateTime.parse("2018-05-08 10:00:00" , dateTimeFormatter);
	}

	public ParqueoTestDataBuilder withFechaIngreso(LocalDateTime fechaIngreso) {

		this.fechaIngreso = fechaIngreso;
		return this;

	}

	public ParqueoTestDataBuilder withFechaRetiro(LocalDateTime fechaRetiro) {

		this.fechaRetiro = fechaRetiro;
		return this;

	}

	public ParqueoTestDataBuilder withVehiculo(Vehiculo vehiculo) {

		this.vehiculo = vehiculo;
		return this;

	}
	
	public Parqueo build() {
		
		return new Parqueo(this.vehiculo, this.fechaIngreso, this.fechaRetiro);
	}

}

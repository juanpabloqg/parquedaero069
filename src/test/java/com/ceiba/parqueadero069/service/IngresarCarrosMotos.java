package com.ceiba.parqueadero069.service;

import com.ceiba.parqueadero069.model.Parqueo;
import com.ceiba.parqueadero069.model.Vehiculo;
import com.ceiba.parqueadero069.testDataBuilder.ParqueoTestDataBuilder;
import com.ceiba.parqueadero069.testDataBuilder.VehiculoTestDataBuilder;

public class IngresarCarrosMotos {
	
	public Vehiculo crearCarro() {
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo("CARRO").build();
		
		return vehiculo;
	}
	
	public Vehiculo crearMoto() {
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo("MOTO").build();
		
		return vehiculo;
	}

	public Parqueo ingresarCarro() {
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo("CARRO").build();
		
		Parqueo parqueo = new ParqueoTestDataBuilder().withVehiculo(vehiculo).build();
		
		return parqueo;
		
	}


	public Parqueo ingresarMoto() {
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo("MOTO").build();
		
		Parqueo parqueo = new ParqueoTestDataBuilder().withVehiculo(vehiculo).build();
		
		return parqueo;
	}
	
	
	
	
	
	

}

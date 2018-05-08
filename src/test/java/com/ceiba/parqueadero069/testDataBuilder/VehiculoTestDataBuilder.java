package com.ceiba.parqueadero069.testDataBuilder;

import com.ceiba.parqueadero069.model.Vehiculo;

public class VehiculoTestDataBuilder {
	
	
	private String tipoVehiculo;
	private String placa;
	
	
	public VehiculoTestDataBuilder() {
		
		this.tipoVehiculo = "CARRO";
		this.placa = "hhh111";
	}
	
	public VehiculoTestDataBuilder withTipoVehiculo(String tipoVehiculo) {
		
		this.tipoVehiculo = tipoVehiculo;
		return this;
		
	}
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		
		this.placa = placa;
		return this;
		
	}
	
	public Vehiculo build() {
		
		return new Vehiculo(this.tipoVehiculo, this.placa);
		
	}
	
	public static Vehiculo aVehiculoTestDataBuilder(){
        return new VehiculoTestDataBuilder().build();
    }
	
	
	
	
	
	
	
	

}

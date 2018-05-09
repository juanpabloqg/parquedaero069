package com.ceiba.parqueadero069.testDataBuilder;

import com.ceiba.parqueadero069.domain.Vehiculo;

public class VehiculoTestDataBuilder {
	
	
	private String tipoVehiculo;
	private String placa;
	private Integer cilindraje;
	
	
	public VehiculoTestDataBuilder() {
		
		this.tipoVehiculo = "CARRO";
		this.placa = "hhh111";
		this.cilindraje = 180;
	}
	
	public VehiculoTestDataBuilder withTipoVehiculo(String tipoVehiculo) {
		
		this.tipoVehiculo = tipoVehiculo;
		return this;
		
	}
	
	public VehiculoTestDataBuilder withPlaca(String placa) {
		
		this.placa = placa;
		return this;
		
	}
	
	public VehiculoTestDataBuilder withCilindraje(Integer cilindraje) {
		
		this.cilindraje = cilindraje;
		return this;
		
	}
	
	public Vehiculo build() {
		
		return new Vehiculo(this.tipoVehiculo, this.placa, this.cilindraje);
		
	}
	
	public static Vehiculo aVehiculoTestDataBuilder(){
        return new VehiculoTestDataBuilder().build();
    }
	
	
	
	
	
	
	
	

}

package com.ceiba.parqueadero069.model;

public class Vehiculo {
	
	private String tipoVehiculo;
	private String placa;
	
	
	
	public Vehiculo(String tipoVehiculo, String placa) {
		
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
	}
	
	public Vehiculo() {
		
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
	
	
	

}

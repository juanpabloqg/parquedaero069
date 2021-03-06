package com.ceiba.parqueadero069.domain;

public class Vehiculo {
	
	private Integer idVehiculo;
	private String tipoVehiculo;
	private String placa;
	private Integer cilindraje;	
	
	public Vehiculo(String tipoVehiculo, String placa, int cilindraje) {
		
		
		this.tipoVehiculo = tipoVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
	}

	public Vehiculo() {
		
	}	

	public Integer getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
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

	public Integer getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
	}
	
	
	
	

}

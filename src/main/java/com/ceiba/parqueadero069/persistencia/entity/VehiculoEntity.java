package com.ceiba.parqueadero069.persistencia.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "vehiculo")
public class VehiculoEntity {

	@Id
	@Column(name = "id_vehiculo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idVehiculo;
	
	@Column(name = "tipo_vehiculo", nullable = false)
	private String tipoVehiculo;
	
	@Column(name = "placa" , nullable = false)
	private String placa; 
	
	@Column(name = "cilindraje", nullable = true)
	private Integer cilindraje;

	public int getIdVehiculo() {
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

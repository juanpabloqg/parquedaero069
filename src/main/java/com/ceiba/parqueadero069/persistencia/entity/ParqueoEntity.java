package com.ceiba.parqueadero069.persistencia.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ceiba.parqueadero069.domain.Vehiculo;

@Entity(name = "parqueo")
public class ParqueoEntity {
	
	@Id
	@Column(name = "id_parqueo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idParqueo;
	
	@ManyToOne
	@JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
	private VehiculoEntity vehiculoEntity;
	
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDateTime fechaIngreso;
	
	@Column(name = "fecha_retiro", nullable = true)
	private LocalDateTime fechaRetiro;

	public Integer getIdParqueo() {
		return idParqueo;
	}

	public void setIdParqueo(Integer idParqueo) {
		this.idParqueo = idParqueo;
	}

	public VehiculoEntity getVehiculoModel() {
		return vehiculoEntity;
	}

	public void setVehiculoModel(VehiculoEntity vehiculoEntity) {
		this.vehiculoEntity = vehiculoEntity;
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

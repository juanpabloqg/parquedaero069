package com.ceiba.parqueadero069.persistencia.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.ceiba.parqueadero069.domain.Vehiculo;

@Entity(name = "movimiento_parqueadero")
//@NamedQuery(name = "movimiento_parqueadero.findByPlaca", query = "SELECT m FROM movimiento_parqueadero m WHERE m.vehiculoEntity.placa = :placa")
public class MovimientoParqueaderoEntity {
	
	@Id
	@Column(name = "id_movimiento_parqueadero")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idParqueo;
	
	@Cascade(CascadeType.ALL)
	@ManyToOne
	@JoinColumn(name = "id_vehiculo", referencedColumnName = "id_vehiculo")
	private VehiculoEntity vehiculoEntity;
	
	@Column(name = "fecha_ingreso", nullable = false)
	private LocalDateTime fechaIngreso;
	
	@Column(name = "fecha_retiro", nullable = true)
	private LocalDateTime fechaRetiro;
	
	@Column(name = "valor_parqueo", nullable = true)
	private BigDecimal valor_parqueo;

	public Integer getIdParqueo() {
		return idParqueo;
	}

	public void setIdParqueo(Integer idParqueo) {
		this.idParqueo = idParqueo;
	}

	public VehiculoEntity getVehiculo() {
		return vehiculoEntity;
	}

	public void setVehiculo(VehiculoEntity vehiculoEntity) {
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
	
	public BigDecimal getValor_parqueo() {
		return valor_parqueo;
	}

	public void setValor_parqueo(BigDecimal valor_parqueo) {
		this.valor_parqueo = valor_parqueo;
	}

	public MovimientoParqueaderoEntity() {
		idParqueo = Integer.valueOf(0);
	}
	
	
	

}

package com.ceiba.parqueadero069.persistencia.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.persistencia.entity.MovimientoParqueaderoEntity;

@Component("movimientoParqueaderoBuilder")
public class MovimientoParqueaderoBuilder {
	
	@Autowired
	@Qualifier("vehiculoBuilder")
	VehiculoBuilder vehiculoBuilder;
	
	
	/**
	 * Metodo que convierte de domain a entity de Parqueo
	 * @param vehiculo
	 * @return
	 */
	public MovimientoParqueaderoEntity converterParqueo2ParqueoEntity(MovimientoParqueadero parqueo) {
		
		MovimientoParqueaderoEntity parqueoEntity = new MovimientoParqueaderoEntity();
		parqueoEntity.setFechaIngreso(parqueo.getFechaIngreso());
		parqueoEntity.setFechaRetiro(parqueo.getFechaRetiro());
		parqueoEntity.setVehiculo(vehiculoBuilder.converterVehiculo2VehiculoEntity(parqueo.getVehiculo()));
		
		return parqueoEntity;
		
	} 
	
	/**
	 * Metodo que convierte de entity a domain de Parqueo
	 * @param vehiculoEntity
	 * @return
	 */
	public MovimientoParqueadero convertParqueoEntity2Parqueo(MovimientoParqueaderoEntity parqueoEntity) {
		
		MovimientoParqueadero parqueo = new MovimientoParqueadero();
		parqueo.setFechaIngreso(parqueoEntity.getFechaIngreso());
		parqueo.setFechaRetiro(parqueoEntity.getFechaRetiro());
		parqueo.setVehiculo(vehiculoBuilder.convertVehiculoEntity2Vehiculo(parqueoEntity.getVehiculo()));
		
		return parqueo;
		
	} 

}

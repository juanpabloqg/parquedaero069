package com.ceiba.parqueadero069.persistencia.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ceiba.parqueadero069.domain.Parqueo;
import com.ceiba.parqueadero069.persistencia.entity.ParqueoEntity;

@Component("parqueoBuilder")
public class ParqueoBuilder {
	
	@Autowired
	@Qualifier("vehiculoBuilder")
	VehiculoBuilder vehiculoBuilder;
	
	
	/**
	 * Metodo que convierte de domain a entity de Parqueo
	 * @param vehiculo
	 * @return
	 */
	public ParqueoEntity converterParqueo2ParqueoEntity(Parqueo parqueo) {
		
		ParqueoEntity parqueoEntity = new ParqueoEntity();
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
	public Parqueo convertParqueoEntity2Parqueo(ParqueoEntity parqueoEntity) {
		
		Parqueo parqueo = new Parqueo();
		parqueo.setFechaIngreso(parqueoEntity.getFechaIngreso());
		parqueo.setFechaRetiro(parqueoEntity.getFechaRetiro());
		parqueo.setVehiculo(vehiculoBuilder.convertVehiculoEntity2Vehiculo(parqueoEntity.getVehiculo()));
		
		return parqueo;
		
	} 

}

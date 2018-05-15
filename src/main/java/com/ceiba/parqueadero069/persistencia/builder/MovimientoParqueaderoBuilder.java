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
	public MovimientoParqueaderoEntity converterParqueo2ParqueoEntity(MovimientoParqueadero movimientoParqueadero) {
		
		MovimientoParqueaderoEntity movimientoParqueaderoEntity = new MovimientoParqueaderoEntity();
		movimientoParqueaderoEntity.setIdParqueo( movimientoParqueadero.getId() );
		movimientoParqueaderoEntity.setFechaIngreso(movimientoParqueadero.getFechaIngreso());
		movimientoParqueaderoEntity.setFechaRetiro(movimientoParqueadero.getFechaRetiro());
		movimientoParqueaderoEntity.setVehiculo(vehiculoBuilder.converterVehiculo2VehiculoEntity(movimientoParqueadero.getVehiculo()));
		movimientoParqueaderoEntity.setValorCobrado(movimientoParqueadero.getValorCobrado());
		movimientoParqueaderoEntity.setEstado(movimientoParqueadero.getEstado());
		
		return movimientoParqueaderoEntity;
		
	} 
	
	/**
	 * Metodo que convierte de entity a domain de Parqueo
	 * @param vehiculoEntity
	 * @return
	 */
	public MovimientoParqueadero convertParqueoEntity2Parqueo(MovimientoParqueaderoEntity movimientoParqueaderoEntity) {
		
		MovimientoParqueadero movimientoParqueadero = new MovimientoParqueadero();
		movimientoParqueadero.setId( movimientoParqueaderoEntity.getIdParqueo() );
		movimientoParqueadero.setFechaIngreso(movimientoParqueaderoEntity.getFechaIngreso());
		movimientoParqueadero.setFechaRetiro(movimientoParqueaderoEntity.getFechaRetiro());
		movimientoParqueadero.setVehiculo(vehiculoBuilder.convertVehiculoEntity2Vehiculo(movimientoParqueaderoEntity.getVehiculo()));
		movimientoParqueadero.setValorCobrado(movimientoParqueaderoEntity.getValorCobrado());
		movimientoParqueadero.setEstado(movimientoParqueaderoEntity.getEstado());
		
		return movimientoParqueadero;
		
	} 

}

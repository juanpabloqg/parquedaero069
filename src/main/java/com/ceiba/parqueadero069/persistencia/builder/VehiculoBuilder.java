package com.ceiba.parqueadero069.persistencia.builder;

import org.springframework.stereotype.Component;

import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.persistencia.entity.VehiculoEntity;

@Component("vehiculoBuilder")
public class VehiculoBuilder {
	
	/**
	 * Metodo que convierte de domain a entity de Vehiculo
	 * @param vehiculo
	 * @return
	 */
	public VehiculoEntity converterVehiculo2VehiculoEntity(Vehiculo vehiculo) {
		
		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setIdVehiculo(vehiculo.getIdVehículo());
		vehiculoEntity.setPlaca(vehiculo.getPlaca());
		vehiculoEntity.setTipoVehiculo(vehiculo.getTipoVehiculo());
		vehiculoEntity.setCilindraje(vehiculo.getCilindraje());
		
		return vehiculoEntity;
		
	} 
	
	/**
	 * Metodo que convierte de entity a domain de vehiculo
	 * @param vehiculoEntity
	 * @return
	 */
	public Vehiculo convertVehiculoEntity2Vehiculo(VehiculoEntity vehiculoEntity) {
		
		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setIdVehículo(vehiculoEntity.getIdVehiculo());
		vehiculo.setPlaca(vehiculoEntity.getPlaca());
		vehiculo.setTipoVehiculo(vehiculoEntity.getTipoVehiculo());
		vehiculo.setCilindraje(vehiculoEntity.getCilindraje());
		
		return vehiculo;
		
	} 
	
	

}

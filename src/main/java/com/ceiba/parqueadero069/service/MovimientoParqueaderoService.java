package com.ceiba.parqueadero069.service;

import java.time.LocalDateTime;
import java.util.List;

import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.persistencia.entity.MovimientoParqueaderoEntity;

public interface MovimientoParqueaderoService {
	
	public abstract String ingresarVehiculo(MovimientoParqueadero movimientoParqueadero);
	
	public abstract void verificarDisponibilidadParqueaderos(String tipoVehiculo);	
	
	public abstract String retirarVehiculo(String placa,  LocalDateTime fechaRetiro);
	
	public abstract MovimientoParqueaderoEntity obtenerVehiculoParqueadoPorPlaca(String placa);
	
	public abstract MovimientoParqueaderoEntity obtenerRetiradoParqueadoPorPlaca(String placa);
	
	public abstract void verificarDisponibilidadPorInicioLetrasPlaca(MovimientoParqueadero movimientoParqueadero);
	
	/**
	 * Metodo que verifica si existe un vehiculo parqueado por su placa
	 * @param placa
	 * @return
	 */
	public abstract boolean esParqueado(String placa);
	

	

}

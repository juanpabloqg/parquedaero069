package com.ceiba.parqueadero069.service;

import java.util.List;

import com.ceiba.parqueadero069.domain.MovimientoParqueadero;

public interface MovimientoParqueaderoService {
	
	public abstract String ingresarVehiculo(MovimientoParqueadero movimientoParqueadero);
	
	public abstract void verificarDisponibilidadParqueaderos(String tipoVehiculo);	
	
	public abstract void retirarVehiculo(MovimientoParqueadero movimientoParqueadero);
	
	public abstract MovimientoParqueadero findByPlaca(String placa);
	
	public abstract void verificarLetrasPlaca(MovimientoParqueadero movimientoParqueadero);
	

	

}

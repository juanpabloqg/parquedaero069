package com.ceiba.parqueadero069.service;

import com.ceiba.parqueadero069.domain.Vehiculo;

public interface VehiculoService {
	
	public abstract Vehiculo addVechiculo(Vehiculo vehiculo);
	
	public abstract void removeVehiculo(Vehiculo vehiculo);
}

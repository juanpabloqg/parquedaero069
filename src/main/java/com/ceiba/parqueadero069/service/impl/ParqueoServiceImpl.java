package com.ceiba.parqueadero069.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero069.domain.Parqueo;
import com.ceiba.parqueadero069.persistencia.builder.ParqueoBuilder;
import com.ceiba.parqueadero069.persistencia.builder.VehiculoBuilder;
import com.ceiba.parqueadero069.persistencia.repository.ParqueoRepository;
import com.ceiba.parqueadero069.persistencia.repository.VehiculoRepository;
import com.ceiba.parqueadero069.service.ParqueoService;

@Service("parqueoService")
public class ParqueoServiceImpl implements ParqueoService  {
	
	private static final String MENSAJE_INGRESO_EXITOSO = "Vehiculo ingresado exitosamente";
	
	@Autowired
	@Qualifier("parqueoRepository")
	ParqueoRepository parqueoRepository;
	
	@Autowired
	@Qualifier("vehiculoRepository")
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	@Qualifier("vehiculoBuilder")
	VehiculoBuilder vehiculoBuilder;
	
	@Autowired
	@Qualifier("parqueoBuilder")
	ParqueoBuilder parqueoBuilder;
	
	
	
	public String ingresarVehiculo (Parqueo parqueo) {
		
		vehiculoRepository.save(vehiculoBuilder.converterVehiculo2VehiculoEntity(parqueo.getVehiculo()));
		
		parqueoRepository.save(parqueoBuilder.converterParqueo2ParqueoEntity(parqueo));
		
		return MENSAJE_INGRESO_EXITOSO;
	}
	
	



	

}

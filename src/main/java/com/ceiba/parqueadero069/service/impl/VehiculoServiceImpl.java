package com.ceiba.parqueadero069.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.persistencia.builder.VehiculoBuilder;
import com.ceiba.parqueadero069.persistencia.entity.VehiculoEntity;
import com.ceiba.parqueadero069.persistencia.repository.VehiculoRepository;
import com.ceiba.parqueadero069.service.VehiculoService;

@Service("vehiculoService")
public class VehiculoServiceImpl implements VehiculoService {
	
	@Autowired
	@Qualifier("vehiculoRepository")
	private VehiculoRepository vehiculoRepository;
	
	@Autowired
	@Qualifier("vehiculoBuilder")
	private VehiculoBuilder vehiculoBuilder;
	
	


	@Override
	public List<Vehiculo> listAllVehiculos() {
		List<Vehiculo> listaVehiculos=  new ArrayList<>();
		
		for (VehiculoEntity vehiculoEntity : vehiculoRepository.findAll()) {
			listaVehiculos.add(vehiculoBuilder.convertVehiculoEntity2Vehiculo(vehiculoEntity));
		}
		return listaVehiculos;
	}
	

}

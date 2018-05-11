package com.ceiba.parqueadero069.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.exception.MovimientoParqueaderoException;
import com.ceiba.parqueadero069.persistencia.builder.VehiculoBuilder;
import com.ceiba.parqueadero069.persistencia.entity.VehiculoEntity;
import com.ceiba.parqueadero069.service.MovimientoParqueaderoService;
import com.ceiba.parqueadero069.service.VehiculoService;
import com.ceiba.parqueadero069.util.RestResponse;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/vehiculos")
public class VigilanteController {
	
	@Autowired
	@Qualifier("vehiculoBuilder")
	private VehiculoBuilder vehiculoBuilder;
	
	@Autowired
	@Qualifier("movimientoParqueaderoService")
	private MovimientoParqueaderoService movimientoParqueaderoService;
	
	@Autowired
	@Qualifier("vehiculoService")
	private VehiculoService vehiculoService;
	
	@PostMapping("/ingresarautomovil")
	public RestResponse ingresarVehiculo(@RequestBody String vehiculoJson) throws JsonParseException, JsonMappingException, IOException {
		
		Vehiculo vehiculo = new ObjectMapper().readValue(vehiculoJson, Vehiculo.class);

		
		MovimientoParqueadero parqueo = new MovimientoParqueadero();
		parqueo.setFechaIngreso(LocalDateTime.now());
		parqueo.setVehiculo(vehiculo);
		
		String respuesta = movimientoParqueaderoService.ingresarVehiculo(parqueo);		 
		
		
		return new RestResponse(HttpStatus.OK.value(),respuesta);
		
		
	}
	
	@GetMapping("/listallvehiculos")
	public List<Vehiculo> listAllVehiculos() {
		
		return vehiculoService.listAllVehiculos();
	}
	
	@PostMapping("/deletevehiculo")
	public RestResponse deleteVehiculo(@RequestBody String placaJson) throws Exception {
		
		MovimientoParqueadero movimientoParqueadero = new ObjectMapper().readValue(placaJson, MovimientoParqueadero.class);
		
		
		movimientoParqueaderoService.retirarVehiculo(movimientoParqueadero);
		
		return new RestResponse(HttpStatus.OK.value(),MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO);		
		
	}
	
	@PostMapping("/retirarvehiculo")
	public RestResponse retirarVehiculo(@RequestBody String placaJson) throws Exception {
		
		MovimientoParqueadero movimientoParqueadero = new ObjectMapper().readValue(placaJson, MovimientoParqueadero.class);
		
		
		movimientoParqueaderoService.retirarVehiculo(movimientoParqueadero);
		
		return new RestResponse(HttpStatus.OK.value(),MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO);		
		
	}
	
	
	
	

}

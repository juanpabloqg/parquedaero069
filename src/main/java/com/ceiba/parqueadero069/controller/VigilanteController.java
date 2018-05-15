package com.ceiba.parqueadero069.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
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
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.std.JsonValueSerializer;

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

		
		MovimientoParqueadero movimientoParqueadero = new MovimientoParqueadero();
		movimientoParqueadero.setFechaIngreso(LocalDateTime.now());
		movimientoParqueadero.setVehiculo(vehiculo);
		movimientoParqueadero.setEstado(MovimientoParqueaderoConstant.ESTADO_INGRESADO);
		
		
		String respuesta = movimientoParqueaderoService.ingresarVehiculo(movimientoParqueadero);		 
		
		
		return new RestResponse(HttpStatus.OK.value(),respuesta);
		
		
	}
	
	@GetMapping("/listallvehiculos")
	public List<Vehiculo> listAllVehiculos() {
		
		return vehiculoService.listAllVehiculos();
	}
	
//	@PostMapping("/deletevehiculo")
//	public RestResponse deleteVehiculo(@RequestBody String placa) throws Exception {
//		
//		
//		return new RestResponse(HttpStatus.OK.value(),MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO);		
//		
//	}
	
	@PostMapping("/retirarvehiculo")
	public RestResponse retirarVehiculo(@RequestBody String placaJson) throws MovimientoParqueaderoException, JSONException {
		
		JSONObject jsonObject = new JSONObject(placaJson);
		
		String placa = jsonObject.getString("placa");		
				
		movimientoParqueaderoService.retirarVehiculo(placa, LocalDateTime.now());
		
		return new RestResponse(HttpStatus.OK.value(),MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO);		
		
	}
	
	
	
	

}

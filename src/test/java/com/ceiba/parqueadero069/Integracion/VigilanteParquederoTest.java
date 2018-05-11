package com.ceiba.parqueadero069.Integracion;

import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.exception.MovimientoParqueaderoException;
import com.ceiba.parqueadero069.persistencia.repository.MovimientoParqueaderoRepository;
import com.ceiba.parqueadero069.service.MovimientoParqueaderoService;
import com.ceiba.parqueadero069.testDataBuilder.MovimientoParqueaderoTestDataBuilder;
import com.ceiba.parqueadero069.testDataBuilder.VehiculoTestDataBuilder;


public class VigilanteParquederoTest {
	
	@Autowired
	@Qualifier("movimientoParqueaderoService")
	MovimientoParqueaderoService movimientoParqueaderoService;
	
	@Autowired
	@Qualifier("movimientoParqueaderoRepository")
	MovimientoParqueaderoRepository movimientoParqueaderoRepository;
	
	
	
	@Before
	public void setUp() {
		
	
	}
	

	
	
	
	

}

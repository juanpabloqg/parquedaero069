package com.ceiba.parqueadero069;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero069.domain.Parqueo;
import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.service.ParqueoService;
import com.ceiba.parqueadero069.testDataBuilder.ParqueoTestDataBuilder;
import com.ceiba.parqueadero069.testDataBuilder.VehiculoTestDataBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Parqueadero069ApplicationTests {
	
	private static final String TIPO_VEHICULO_CARRO = "CARRO";
	private static final String TIPO_VEHICULO_MOTO = "MOTO";
	private static final String PLACA_CARRO = "HMV545";
	private static final String PLACA_MOTO = "HLV54D";
	private static final String FORMATO_FECHA = "yyyy-MM-dd HH:mm:ss";
	private static final String FECHA_INGRESO = "2018-05-09 08:00:00";
	private static final String MENSAJE_INGRESO_EXITO_CARRO = "Carro ingresado exitosamente";
	private static final String MENSAJE_INGRESO_EXITO_MOTO = "Moto ingresada exitosamente";
	
	private static final Integer VALOR_CARRO_HORA = 1000;
	private static final Integer VALOR_MOTO_HORA = 500;
	private static final Integer VALOR_DIA_CARRO = 8000;
	private static final Integer VALOR_DIA_MOTO = 4000;
	private static final Integer CAPACIDAD_MAXIMA_CARRO = 20;
	private static final Integer CAPACIDAD_MAXIMA_MOTOS = 10;
	
	
	@Autowired
	@Qualifier("parqueoService")
	private ParqueoService parqueoService;
	
	@Before
	public void setup() {
		
		parqueoService = Mockito.mock(ParqueoService.class);

	}

	
	@Test
	public void ingresarCarro() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(TIPO_VEHICULO_CARRO).withPlaca(PLACA_CARRO).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(FECHA_INGRESO, dateTimeFormatter);		
		
				
		//Action
		Parqueo parqueo = new ParqueoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		Mockito.when(parqueoService.ingresarCarro(parqueo)).thenReturn(MENSAJE_INGRESO_EXITO_CARRO);
		
		
		//Assert
		assertEquals(PLACA_CARRO, parqueo.getVehiculoModel().getPlaca());
		assertEquals(TIPO_VEHICULO_CARRO, parqueo.getVehiculoModel().getTipoVehiculo());		
		assertEquals(fechaIngreso, parqueo.getFechaIngreso());
		assertEquals(MENSAJE_INGRESO_EXITO_CARRO,parqueoService.ingresarCarro(parqueo));
			
		
		
	}
	
	@Test
	public void ingresarMoto() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(TIPO_VEHICULO_MOTO).withPlaca(PLACA_MOTO).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(FECHA_INGRESO, dateTimeFormatter);		
		
				
		//Action
		Parqueo parqueo = new ParqueoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		Mockito.when(parqueoService.ingresarCarro(parqueo)).thenReturn(MENSAJE_INGRESO_EXITO_MOTO);
		
		
		//Assert
		assertEquals(PLACA_MOTO, parqueo.getVehiculoModel().getPlaca());
		assertEquals(TIPO_VEHICULO_MOTO, parqueo.getVehiculoModel().getTipoVehiculo());		
		assertEquals(fechaIngreso, parqueo.getFechaIngreso());
		assertEquals(MENSAJE_INGRESO_EXITO_MOTO,parqueoService.ingresarCarro(parqueo));
			
		
		
	}

}

package com.ceiba.parqueadero069.unitarias;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.exception.MovimientoParqueaderoException;
import com.ceiba.parqueadero069.persistencia.repository.MovimientoParqueaderoRepository;
import com.ceiba.parqueadero069.service.impl.MovimientoParqueaderoServiceImpl;
import com.ceiba.parqueadero069.testDataBuilder.MovimientoParqueaderoTestDataBuilder;
import com.ceiba.parqueadero069.testDataBuilder.VehiculoTestDataBuilder;



@RunWith(SpringRunner.class)
@SpringBootTest
public class VigilanteParqueaderoTest {
	
	private static final String TIPO_VEHICULO_CARRO = "CARRO";
	private static final String TIPO_VEHICULO_MOTO = "MOTO";
	private static final String PLACA_CARRO = "HMV545";
	private static final String PLACA_MOTO = "HLV54D";
	private static final String FORMATO_FECHA = "yyyy-MM-dd HH:mm:ss";
	private static final String FECHA_INGRESO = "2018-05-09 08:00:00";
	private static final String MENSAJE_INGRESO_EXITOSO = "Vehiculo ingresado exitosamente";
	private static final String MENSAJE_NO_DISPONIBILIDAD_PARQUEO = "No hay mas capacidad en el paqueadero";
	
	private static final Integer VALOR_CARRO_HORA = 1000;
	private static final Integer VALOR_MOTO_HORA = 500;
	private static final Integer VALOR_DIA_CARRO = 8000;
	private static final Integer VALOR_DIA_MOTO = 4000;
	private static final Integer CAPACIDAD_MAXIMA_CARRO = 20;
	private static final Integer CAPACIDAD_MAXIMA_MOTOS = 10;
	
	
	@Autowired
	@Qualifier("movimientoParqueaderoService")
	private MovimientoParqueaderoServiceImpl movimientoParqueaderoService;
	
	@Autowired
	@Qualifier("movimientoParqueaderoRepository")
	private MovimientoParqueaderoRepository movimientoParqueaderoRepository;
	
	@Before
	public void setup() {
		
		movimientoParqueaderoService = Mockito.mock(MovimientoParqueaderoServiceImpl.class);
		movimientoParqueaderoRepository = Mockito.mock(MovimientoParqueaderoRepository.class);

	}

	
	@Test
	public void ingresarCarro() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(TIPO_VEHICULO_CARRO).withPlaca(PLACA_CARRO).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(FECHA_INGRESO, dateTimeFormatter);		
		
				
		//Action
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		Mockito.when(movimientoParqueaderoService.ingresarVehiculo(parqueo)).thenReturn(MENSAJE_INGRESO_EXITOSO);
		
		
		//Assert
		assertEquals(PLACA_CARRO, parqueo.getVehiculo().getPlaca());
		assertEquals(TIPO_VEHICULO_CARRO, parqueo.getVehiculo().getTipoVehiculo());		
		assertEquals(fechaIngreso, parqueo.getFechaIngreso());
		assertEquals(MENSAJE_INGRESO_EXITOSO,movimientoParqueaderoService.ingresarVehiculo(parqueo));
			
		
		
	}
	
	@Test
	public void ingresarMoto() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(TIPO_VEHICULO_MOTO).withPlaca(PLACA_MOTO).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(FECHA_INGRESO, dateTimeFormatter);		
		
				
		//Action
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		Mockito.when(movimientoParqueaderoService.ingresarVehiculo(parqueo)).thenReturn(MENSAJE_INGRESO_EXITOSO);
		
		
		//Assert
		assertEquals(PLACA_MOTO, parqueo.getVehiculo().getPlaca());
		assertEquals(TIPO_VEHICULO_MOTO, parqueo.getVehiculo().getTipoVehiculo());		
		assertEquals(fechaIngreso, parqueo.getFechaIngreso());
		assertEquals(MENSAJE_INGRESO_EXITOSO,movimientoParqueaderoService.ingresarVehiculo(parqueo));
			
		
		
	}
	
	@Test
	public void ingresaCarroSinDisponibilidadMasde20() {
		
		//Arrange		
				
		//Action
				
		try {
			
			Mockito.doThrow(new MovimientoParqueaderoException(MENSAJE_NO_DISPONIBILIDAD_PARQUEO))
			.when(movimientoParqueaderoService)
				.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
			
			fail();
			
			
		} catch (MovimientoParqueaderoException e) {
			// assert
			Assert.assertEquals(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO, e.getMessage());
		}
		
	}
	
	
	
	
	
	
	
	

}

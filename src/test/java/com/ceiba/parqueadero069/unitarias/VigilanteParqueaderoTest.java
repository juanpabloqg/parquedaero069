package com.ceiba.parqueadero069.unitarias;



import static org.junit.Assert.fail;

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
import com.ceiba.parqueadero069.controller.VigilanteController;
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
		
	
	@Autowired
	@Qualifier("movimientoParqueaderoService")
	private MovimientoParqueaderoServiceImpl movimientoParqueaderoService;
	
	@Autowired
	@Qualifier("movimientoParqueaderoRepository")
	private MovimientoParqueaderoRepository movimientoParqueaderoRepository;
	
	@Autowired
	@Qualifier("vigilanteController")
	private VigilanteController vigilanteController;
	
	@Before
	public void setup() {
		
		movimientoParqueaderoService = Mockito.mock(MovimientoParqueaderoServiceImpl.class);
		movimientoParqueaderoRepository = Mockito.mock(MovimientoParqueaderoRepository.class);
		vigilanteController = Mockito.mock(VigilanteController.class);

	}	
	
	
	@Test
	public void verificarNoDisponibilidadMasDe20Carros() {
		
		//Arrange		
				
		//Action
				
		try {
			
			Mockito.doThrow(new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO))
				.when(movimientoParqueaderoService)
				.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
			
			movimientoParqueaderoService.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
			fail();
			
			
		} catch (MovimientoParqueaderoException e) {
			// assert
			Assert.assertEquals(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO, e.getMessage());
		}
		
	}
	
	
	@Test
	public void verificarNoDisponibilidadMasDe10Motos() {
		
		//Arrange		
				
		//Action
				
		try {
			
			Mockito.doThrow(new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO))
			.when(movimientoParqueaderoService)
				.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO);
			
			movimientoParqueaderoService.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO);
			fail();
			
			
		} catch (MovimientoParqueaderoException e) {
			// assert
			Assert.assertEquals(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO, e.getMessage());
		}
		
	}
	
	@Test
	public void verificarNoDisponibilidadMasDe20CarrosJP() {
		
		//Arrange	
		
		int cantidadCarros = 0;
		
		//Action
				
		try {
						
			Mockito.when(movimientoParqueaderoRepository.countVehiculos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO)).thenReturn(20);
			
			cantidadCarros = movimientoParqueaderoRepository.countVehiculos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
			
			Mockito.doThrow(new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO))
				.when(movimientoParqueaderoService)
				.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
			
			movimientoParqueaderoService.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
			
			fail();
			
			
		} catch (MovimientoParqueaderoException e) {
			// assert
			
			Assert.assertEquals(20, cantidadCarros);
			Assert.assertEquals(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO, e.getMessage());
		}
		
		
		
	}
	
	@Test
	public void verificarNoDisponibilidadMasDe10Motos_() {
		
		//Arrange
		
		int cantidadMotos = 0;
				
		//Action
				
		try {
			
			Mockito.when(movimientoParqueaderoRepository.countVehiculos(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO)).thenReturn(10);
			
			Mockito.doThrow(new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO))
			.when(movimientoParqueaderoService)
				.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO);
			
			cantidadMotos = movimientoParqueaderoRepository.countVehiculos(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO);
			
			movimientoParqueaderoService.verificarDisponibilidadParqueaderos(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO);
			
			fail();
			
			
		} catch (MovimientoParqueaderoException e) {
			// assert
			Assert.assertEquals(10, cantidadMotos);
			Assert.assertEquals(MovimientoParqueaderoConstant.MENSAJE_NO_DISPONIBILIDAD_PARQUEO, e.getMessage());
		}
		
	}
	
	@Test
	public void verificarNoDisponibilidadCarroPlacaIniciaConAEnDiasDistintosLunesDomingo() {
		
		//Arrange
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO).withPlaca(MovimientoParqueaderoConstant.PLACA_CARRO_INICIA_CON_A).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO_NO_LUNES_NO_DOMINGO, dateTimeFormatter);	
		
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
				
		//Action
				
		try {			
			
			Mockito.doThrow(new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_ERROR_INGRESO_VEHICULO_PLACA_INICIA_A))
			.when(movimientoParqueaderoService)
				.verificarDisponibilidadPorInicioLetrasPlaca(parqueo);
			
			movimientoParqueaderoService.verificarDisponibilidadPorInicioLetrasPlaca(parqueo);
			
			fail();
			
			
		} catch (MovimientoParqueaderoException e) {
			// assert
			Assert.assertEquals(MovimientoParqueaderoConstant.MENSAJE_ERROR_INGRESO_VEHICULO_PLACA_INICIA_A, e.getMessage());
		}
		
	}
	
	@Test
	public void verificarNoDisponibilidadMotoPlacaIniciaConAEnDiasDistintosLunesDomingo() {
		
		//Arrange
		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO).withPlaca(MovimientoParqueaderoConstant.PLACA_CARRO_INICIA_CON_A).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO_NO_LUNES_NO_DOMINGO, dateTimeFormatter);	
		
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
				
		//Action
				
		try {			
			
			Mockito.doThrow(new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_ERROR_INGRESO_VEHICULO_PLACA_INICIA_A))
			.when(movimientoParqueaderoService)
				.verificarDisponibilidadPorInicioLetrasPlaca(parqueo);
			
			movimientoParqueaderoService.verificarDisponibilidadPorInicioLetrasPlaca(parqueo);
			
			fail();
			
			
		} catch (MovimientoParqueaderoException e) {
			// assert
			Assert.assertEquals(MovimientoParqueaderoConstant.MENSAJE_ERROR_INGRESO_VEHICULO_PLACA_INICIA_A, e.getMessage());
		}
		
	}
	
	
	
	
		
	
		
	
	

}

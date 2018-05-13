package com.ceiba.parqueadero069.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.persistencia.repository.MovimientoParqueaderoRepository;
import com.ceiba.parqueadero069.persistencia.sistema.SistemaPersistencia;
import com.ceiba.parqueadero069.service.MovimientoParqueaderoService;
import com.ceiba.parqueadero069.testDataBuilder.MovimientoParqueaderoTestDataBuilder;
import com.ceiba.parqueadero069.testDataBuilder.VehiculoTestDataBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VigilanteParquederoTest {
	
	@Autowired
	@Qualifier("movimientoParqueaderoService")
	private MovimientoParqueaderoService movimientoParqueaderoService;
	
	private SistemaPersistencia sistenaPersistencia;
	
	
	
	@Before
	public void setUp() {
		
		
		
	}
	

	@Test
	public void ingresarCarro() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO).withPlaca(MovimientoParqueaderoConstant.PLACA_CARRO).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO, dateTimeFormatter);	
		
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
				
		//Action
		
		String respuestaIngreso = movimientoParqueaderoService.ingresarVehiculo(parqueo);
		
		
		//Assert
		assertEquals(MovimientoParqueaderoConstant.MENSAJE_INGRESO_EXITOSO,respuestaIngreso);	
		assertTrue(movimientoParqueaderoService.esParqueado(vehiculo.getPlaca()));
		
	}
	
	@Test
	public void ingresarMoto() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO).withPlaca(MovimientoParqueaderoConstant.PLACA_MOTO).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO, dateTimeFormatter);	
		
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
				
		//Action
		
		String respuestaIngreso = movimientoParqueaderoService.ingresarVehiculo(parqueo);
		
		
		//Assert
		assertEquals(MovimientoParqueaderoConstant.MENSAJE_INGRESO_EXITOSO,respuestaIngreso);	
		assertTrue(movimientoParqueaderoService.esParqueado(vehiculo.getPlaca()));
		
	}
	
	@Test
	public void ingresarCarroPlacaIniciaAunDomingo() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO).withPlaca(MovimientoParqueaderoConstant.PLACA_CARRO_INICA_CON_A).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO_DOMINGO, dateTimeFormatter);	
		
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
				
		//Action
		
		String respuestaIngreso = movimientoParqueaderoService.ingresarVehiculo(parqueo);
		
		
		//Assert
		assertEquals(MovimientoParqueaderoConstant.MENSAJE_INGRESO_EXITOSO,respuestaIngreso);	
		assertTrue(movimientoParqueaderoService.esParqueado(vehiculo.getPlaca()));
		
	}
	
	@Test
	public void ingresarMotoPlacaIniciaADomingo() {
		
		//Arrange		
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO).withPlaca(MovimientoParqueaderoConstant.PLACA_MOTO_INICA_CON_A).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO_DOMINGO, dateTimeFormatter);	
		
		MovimientoParqueadero parqueo = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
				
		//Action
		
		String respuestaIngreso = movimientoParqueaderoService.ingresarVehiculo(parqueo);
		
		
		//Assert
		assertEquals(MovimientoParqueaderoConstant.MENSAJE_INGRESO_EXITOSO,respuestaIngreso);	
		assertTrue(movimientoParqueaderoService.esParqueado(vehiculo.getPlaca()));
		
	}
	
	
	

}

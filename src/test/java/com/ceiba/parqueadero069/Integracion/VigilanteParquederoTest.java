package com.ceiba.parqueadero069.Integracion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;
import com.ceiba.parqueadero069.persistencia.entity.MovimientoParqueaderoEntity;
import com.ceiba.parqueadero069.service.MovimientoParqueaderoService;
import com.ceiba.parqueadero069.testDataBuilder.MovimientoParqueaderoTestDataBuilder;
import com.ceiba.parqueadero069.testDataBuilder.VehiculoTestDataBuilder;


@RunWith(SpringRunner.class)
@SpringBootTest
public class VigilanteParquederoTest {
	
	@Autowired
	@Qualifier("movimientoParqueaderoService")
	private MovimientoParqueaderoService movimientoParqueaderoService;
	
	
	
	
	
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
		
		MovimientoParqueadero movimientoParqueadero = new MovimientoParqueaderoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
				
		//Action		
		String respuestaIngreso = movimientoParqueaderoService.ingresarVehiculo(movimientoParqueadero);
		
		
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
	
	@Test
	public void ingresarMotoCilinidrajeMas500() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO)
				.withPlaca("HLG54D")
				.withCilindraje(600).build();
		
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
	public void retirarCarro() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO).withPlaca(MovimientoParqueaderoConstant.PLACA_CARRO).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO, dateTimeFormatter);	
		
		LocalDateTime fechaRetiro = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_RETIRO, dateTimeFormatter);
		
		MovimientoParqueadero movimientoParqueadero = new MovimientoParqueaderoTestDataBuilder()
				.withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
		movimientoParqueaderoService.ingresarVehiculo(movimientoParqueadero);
		
				
		//Action		
		
		String respuestaRetiro = movimientoParqueaderoService.retirarVehiculo(movimientoParqueadero.getVehiculo().getPlaca(),fechaRetiro);
		
		
		//Assert
		assertEquals(MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO,respuestaRetiro);	
		assertFalse(movimientoParqueaderoService.esParqueado(vehiculo.getPlaca()));
		
	}
	
	@Test
	public void retirarCarro1Dia3Horas() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO)
				.withPlaca("HMT345").build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO, dateTimeFormatter);
		
		LocalDateTime fechaRetiro = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_RETIRO_1DIA_3HORAS, dateTimeFormatter);
		
		MovimientoParqueadero movimientoParqueadero = new MovimientoParqueaderoTestDataBuilder()
				.withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
		movimientoParqueaderoService.ingresarVehiculo(movimientoParqueadero);
		
				
		//Action		
		
		String respuestaRetiro = movimientoParqueaderoService.retirarVehiculo(movimientoParqueadero.getVehiculo().getPlaca(),fechaRetiro);
		MovimientoParqueaderoEntity movimientoParqueaderoEntity = movimientoParqueaderoService.obtenerRetiradoParqueadoPorPlaca(movimientoParqueadero.getVehiculo().getPlaca());
		
		//Assert
		assertEquals(MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO,respuestaRetiro);	
		assertFalse(movimientoParqueaderoService.esParqueado(vehiculo.getPlaca()));
		assertEquals(new BigInteger("11000"), movimientoParqueaderoEntity.getValorCobrado().toBigInteger());
		
	}
	
	@Test
	public void retirarMoto10HorasCilindraje650() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO)
				.withPlaca("HMT34C").withCilindraje(650).build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);

		LocalDateTime fechaIngreso = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_INGRESO, dateTimeFormatter);
		
		LocalDateTime fechaRetiro = LocalDateTime.parse(MovimientoParqueaderoConstant.FECHA_RETIRO_10_HORAS, dateTimeFormatter);
		
		MovimientoParqueadero movimientoParqueadero = new MovimientoParqueaderoTestDataBuilder()
				.withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		
		movimientoParqueaderoService.ingresarVehiculo(movimientoParqueadero);
		
				
		//Action		
		
		String respuestaRetiro = movimientoParqueaderoService.retirarVehiculo(movimientoParqueadero.getVehiculo().getPlaca(),fechaRetiro);
		MovimientoParqueaderoEntity movimientoParqueaderoEntity = movimientoParqueaderoService.obtenerRetiradoParqueadoPorPlaca(movimientoParqueadero.getVehiculo().getPlaca());
		
		//Assert
		assertEquals(MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO,respuestaRetiro);	
		assertFalse(movimientoParqueaderoService.esParqueado(vehiculo.getPlaca()));
		assertEquals(new BigInteger("6000"), movimientoParqueaderoEntity.getValorCobrado().toBigInteger());
		
	}
	
	
	

}

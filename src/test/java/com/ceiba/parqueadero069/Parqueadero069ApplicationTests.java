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
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo("CARRO").withPlaca("HMV545").build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime fechaIngreso = LocalDateTime.parse("2018-05-09 08:00:00", dateTimeFormatter);		
		
				
		//Action
		Parqueo parqueo = new ParqueoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		Mockito.when(parqueoService.ingresarCarro(parqueo)).thenReturn("Carro ingresado exitosamente");
		
		
		//Assert
		assertEquals("HMV545", parqueo.getVehiculoModel().getPlaca());
		assertEquals("CARRO", parqueo.getVehiculoModel().getTipoVehiculo());		
		assertEquals(fechaIngreso, parqueo.getFechaIngreso());
		assertEquals("Carro ingresado exitosamente",parqueoService.ingresarCarro(parqueo));
			
		
		
	}
	
	@Test
	public void ingresarMoto() {
		
		//Arrange
		Vehiculo vehiculo = new VehiculoTestDataBuilder().withTipoVehiculo("MOTO").withPlaca("HLV54D").build();
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		LocalDateTime fechaIngreso = LocalDateTime.parse("2018-05-09 08:00:00", dateTimeFormatter);		
		
				
		//Action
		Parqueo parqueo = new ParqueoTestDataBuilder().withVehiculo(vehiculo).withFechaIngreso(fechaIngreso).build();
		Mockito.when(parqueoService.ingresarCarro(parqueo)).thenReturn("Carro ingresado exitosamente");
		
		
		//Assert
		assertEquals("HLV54D", parqueo.getVehiculoModel().getPlaca());
		assertEquals("MOTO", parqueo.getVehiculoModel().getTipoVehiculo());		
		assertEquals(fechaIngreso, parqueo.getFechaIngreso());
		assertEquals("Carro ingresado exitosamente",parqueoService.ingresarCarro(parqueo));
			
		
		
	}

}

package com.ceiba.parqueadero069.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.ceiba.parqueadero069.model.Parqueo;
import com.ceiba.parqueadero069.model.Vehiculo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Parqueadero0699ApplicationTests {

	/**
	 * 
	 */
	@Test
	public void crearCarro() {
		
		//Arrange
		IngresarCarrosMotos ingresarCarrosMotos = new IngresarCarrosMotos();
		
		//Action
		Vehiculo  vehiculo = ingresarCarrosMotos.crearCarro();
		
		//Assert
		assertNotNull(vehiculo);		
		
	}
	
	@Test
	public void crearMoto() {
		
		//Arrange
		IngresarCarrosMotos ingresarCarrosMotos = new IngresarCarrosMotos();
		
		//Action
		Vehiculo  vehiculo = ingresarCarrosMotos.crearMoto();
		
		//Assert
		assertNotNull(vehiculo);		
		
	}
	
	@Test
	public void ingresarCarro() {
		
		//Arrange
		IngresarCarrosMotos ingresarCarrosMotos = new IngresarCarrosMotos();
		
		//Action
		Parqueo  parqueo = ingresarCarrosMotos.ingresarCarro();
		
		//Assert
		assertNotNull(parqueo);		
		
	}
	
	@Test
	public void ingresarMoto() {
		
		//Arrange
		IngresarCarrosMotos ingresarCarrosMotos = new IngresarCarrosMotos();
		
		//Action
		Parqueo  parqueo = ingresarCarrosMotos.ingresarMoto();
		
		//Assert
		assertNotNull(parqueo);		
		
	}

}

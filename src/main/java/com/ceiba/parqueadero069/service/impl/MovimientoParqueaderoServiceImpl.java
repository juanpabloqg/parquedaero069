package com.ceiba.parqueadero069.service.impl;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.exception.MovimientoParqueaderoException;
import com.ceiba.parqueadero069.persistencia.builder.MovimientoParqueaderoBuilder;
import com.ceiba.parqueadero069.persistencia.builder.VehiculoBuilder;
import com.ceiba.parqueadero069.persistencia.entity.MovimientoParqueaderoEntity;
import com.ceiba.parqueadero069.persistencia.repository.MovimientoParqueaderoRepository;
import com.ceiba.parqueadero069.persistencia.repository.VehiculoRepository;
import com.ceiba.parqueadero069.service.MovimientoParqueaderoService;

@Service("movimientoParqueaderoService")
public class MovimientoParqueaderoServiceImpl implements MovimientoParqueaderoService  {
	
	private static final String MENSAJE_INGRESO_EXITOSO = "Vehiculo ingresado exitosamente";
	private static final String MENSAJE_NO_DISPONIBILIDAD_PARQUEO = "No hay mas capacidad en el paqueadero";
	
	@Autowired
	@Qualifier("movimientoParqueaderoRepository")
	MovimientoParqueaderoRepository movimientoParqueaderoRepository;
	
	@Autowired
	@Qualifier("vehiculoRepository")
	VehiculoRepository vehiculoRepository;
	
	@Autowired
	@Qualifier("vehiculoBuilder")
	VehiculoBuilder vehiculoBuilder;
	
	@Autowired
	@Qualifier("movimientoParqueaderoBuilder")
	MovimientoParqueaderoBuilder movimientoParqueaderoBuilder;
	
	
	
	public String ingresarVehiculo (MovimientoParqueadero movimientoParqueadero) {
		
		verificarDisponibilidadParqueaderos(movimientoParqueadero.getVehiculo().getTipoVehiculo());
		
		verificarDisponibilidadPorInicioLetrasPlaca(movimientoParqueadero);
		
		
		movimientoParqueaderoRepository.save(movimientoParqueaderoBuilder.converterParqueo2ParqueoEntity(movimientoParqueadero));
		
		return MENSAJE_INGRESO_EXITOSO;
	}



	@Override
	public void verificarDisponibilidadParqueaderos(String tipoVehiculo) {
		
		
		Integer cantidadCarros;
		Integer cantidadMotos;
		
		if (tipoVehiculo.equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO)) {
			
			cantidadCarros = movimientoParqueaderoRepository.countVehiculos(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
			
			if (cantidadCarros > MovimientoParqueaderoConstant.CAPACIDAD_MAXIMA_CARRO) {
				
				throw new MovimientoParqueaderoException(MENSAJE_NO_DISPONIBILIDAD_PARQUEO);
				
			}
			
		} else if (tipoVehiculo.equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO)) {
			
			cantidadMotos = movimientoParqueaderoRepository.countVehiculos(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO);
			
			if (cantidadMotos > MovimientoParqueaderoConstant.CAPACIDAD_MAXIMA_MOTOS) {
				
				throw new MovimientoParqueaderoException(MENSAJE_NO_DISPONIBILIDAD_PARQUEO);
				
			}

		}		
		
	}
	
	

	@Override
	public MovimientoParqueadero obtenerVehiculoParqueadoPorPlaca(String placa) {
		MovimientoParqueaderoEntity movimientoParqueaderoEntity = movimientoParqueaderoRepository.obtenerVehiculoParqueadoPorPlaca(placa);
		return movimientoParqueaderoBuilder.convertParqueoEntity2Parqueo(movimientoParqueaderoEntity);
	}

	@Override
	public void retirarVehiculo(MovimientoParqueadero movimientoParqueadero) {
		
		movimientoParqueaderoRepository.delete(movimientoParqueaderoBuilder.converterParqueo2ParqueoEntity(movimientoParqueadero));
		
	}



	@Override
	public void verificarDisponibilidadPorInicioLetrasPlaca(MovimientoParqueadero movimientoParqueadero) {

		if (movimientoParqueadero.getVehiculo().getPlaca().charAt(0) == 'A') {

			if (!movimientoParqueadero.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				if (movimientoParqueadero.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.MONDAY)) {
					throw new MovimientoParqueaderoException(
							MovimientoParqueaderoConstant.MENSAJE_ERROR_INGRESO_VEHICULO_PLACA_INICIA_A);
				}
			}
		}

	}



	@Override
	public boolean esParqueado(String placa) {
		
		return Optional
				.ofNullable(movimientoParqueaderoRepository.obtenerVehiculoParqueadoPorPlaca(placa))
				.isPresent();
	}
	
	



	



	



	

}

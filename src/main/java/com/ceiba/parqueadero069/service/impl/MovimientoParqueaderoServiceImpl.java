package com.ceiba.parqueadero069.service.impl;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ceiba.parqueadero069.constantes.MovimientoParqueaderoConstant;
import com.ceiba.parqueadero069.domain.MovimientoParqueadero;
import com.ceiba.parqueadero069.domain.Vehiculo;
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
		
		if(esParqueado(movimientoParqueadero.getVehiculo().getPlaca())) {
			throw new  MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_ERRRO_VEHICULO_YA_EXISTE);
		}
		
		verificarDisponibilidadParqueaderos(movimientoParqueadero.getVehiculo().getTipoVehiculo());
		
		verificarDisponibilidadPorInicioLetrasPlaca(movimientoParqueadero);
		
		
		movimientoParqueaderoRepository.save(movimientoParqueaderoBuilder.converterParqueo2ParqueoEntity(movimientoParqueadero));
		
		return MENSAJE_INGRESO_EXITOSO;
	}

	@Override
	public void verificarDisponibilidadParqueaderos(String tipoVehiculo) {
		
		
		if (esCarro(tipoVehiculo)) {
			
			contarVehiculos(tipoVehiculo, MovimientoParqueaderoConstant.CAPACIDAD_MAXIMA_CARRO);
			
		} else if (esMoto(tipoVehiculo)) {
			
			contarVehiculos(tipoVehiculo, MovimientoParqueaderoConstant.CAPACIDAD_MAXIMA_MOTOS);
		}		
		
	}

	private boolean esMoto(String tipoVehiculo) {
		return tipoVehiculo.equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO);
	}

	private boolean esCarro(String tipoVehiculo) {
		return tipoVehiculo.equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO);
	}
	
	public void contarVehiculos(String tipoVehiculo, Integer capacidadMaxima) {
		
		Integer cantidadVehiculos = movimientoParqueaderoRepository.countVehiculos(tipoVehiculo);
		
		if(cantidadVehiculos > capacidadMaxima) {
			
			throw new MovimientoParqueaderoException(MENSAJE_NO_DISPONIBILIDAD_PARQUEO);
		}
		
	}
	
	public MovimientoParqueadero obtenerVehiculoParqueadoPorPlaca( String placa) {
		return movimientoParqueaderoBuilder.convertParqueoEntity2Parqueo( obtenerVehiculoParqueadoPorPlacaEntity(placa)  );
	}

	@Override
	public MovimientoParqueaderoEntity obtenerVehiculoParqueadoPorPlacaEntity(String placa) {
		return movimientoParqueaderoRepository.obtenerVehiculoParqueadoPorPlaca(placa);
	}

	@Override
	public String retirarVehiculo(String placa, LocalDateTime fechaRetiro) {
		
		MovimientoParqueadero movimientoParqueadero = Optional
				.ofNullable(obtenerVehiculoParqueadoPorPlaca(placa))
				.orElseThrow(() -> new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_ERRRO_VEHICULO_NO_EXISTE));
		
		movimientoParqueadero.setFechaRetiro(fechaRetiro);
		
		BigDecimal valorCobrado = calcularCobroVehiculo( movimientoParqueadero.getVehiculo(), movimientoParqueadero.getFechaIngreso(), movimientoParqueadero.getFechaRetiro() );
		
		movimientoParqueadero.setEstado( MovimientoParqueaderoConstant.ESTADO_RETIRADO );
		movimientoParqueadero.setValorCobrado( valorCobrado );
		
		movimientoParqueaderoRepository.save( movimientoParqueaderoBuilder.converterParqueo2ParqueoEntity(movimientoParqueadero) );
		
		return MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO;
		
	}



	@Override
	public void verificarDisponibilidadPorInicioLetrasPlaca(MovimientoParqueadero movimientoParqueadero) {

		if (extraerPrimerLetraPlaca(movimientoParqueadero) == 'A' && !esDomingoLunes(movimientoParqueadero) ) {
			throw new MovimientoParqueaderoException(
					MovimientoParqueaderoConstant.MENSAJE_ERROR_INGRESO_VEHICULO_PLACA_INICIA_A);
		}

	}

	private char extraerPrimerLetraPlaca(MovimientoParqueadero movimientoParqueadero) {
		return movimientoParqueadero.getVehiculo().getPlaca().charAt(0);
	}

	private boolean esDomingoLunes(MovimientoParqueadero movimientoParqueadero) {
		return movimientoParqueadero.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.SUNDAY) || movimientoParqueadero.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.MONDAY);
	}


	@Override
	public boolean esParqueado(String placa) {
		
		return Optional
				.ofNullable(movimientoParqueaderoRepository.obtenerVehiculoParqueadoPorPlaca(placa))
				.isPresent();
	}
	
	

	
	private BigDecimal calcularCobroVehiculo(Vehiculo vehiculo, LocalDateTime fechaIngreso, LocalDateTime fechaRetiro) {
		
		Duration duration = Duration.between(fechaIngreso, fechaRetiro);
		double cantidadHorasParqueado = (((double)duration.toMinutes())/60);
		
		BigDecimal valorCobrado;
		
		
		if (cantidadHorasParqueado > MovimientoParqueaderoConstant.CANTIDAD_HORAS_LIMITE_VEHICULO) {
			
			valorCobrado = cobrarPorDias(vehiculo, cantidadHorasParqueado);			
			
		}else {
			
			valorCobrado = cobrarPorHoras(vehiculo.getTipoVehiculo(), cantidadHorasParqueado);
			
		}
		
		return valorCobrado;
		
	}



	private BigDecimal cobrarPorHoras(String tipoVehiculo, double cantidadHorasParqueado) {
		
		if(esCarro(tipoVehiculo)) {
			return  calculaHoraPorTipoVehiculo(cantidadHorasParqueado, MovimientoParqueaderoConstant.VALOR_CARRO_HORA);
			
		}else {
			return calculaHoraPorTipoVehiculo(cantidadHorasParqueado, MovimientoParqueaderoConstant.VALOR_MOTO_HORA);
		}
		
	}

	private BigDecimal calculaHoraPorTipoVehiculo(double cantidadHorasParqueado, Integer valorPorTipoVehiculo) {
		return BigDecimal.valueOf((cantidadHorasParqueado) * valorPorTipoVehiculo);
	}

	private BigDecimal cobrarPorDias(Vehiculo vehiculo, double cantidadHorasParqueado) {
		BigDecimal valorCobrado = BigDecimal.ZERO;
		
		if(vehiculo.getTipoVehiculo().equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO)) {
			valorCobrado = calcularCobroPorDias(vehiculo.getCilindraje(), cantidadHorasParqueado, MovimientoParqueaderoConstant.VALOR_DIA_CARRO, MovimientoParqueaderoConstant.VALOR_CARRO_HORA);
		}else if(vehiculo.getTipoVehiculo().equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO)){
			valorCobrado = calcularCobroPorDias(vehiculo.getCilindraje(), cantidadHorasParqueado, MovimientoParqueaderoConstant.VALOR_DIA_MOTO, MovimientoParqueaderoConstant.VALOR_MOTO_HORA);
		}
		
		return valorCobrado;
	}

	private BigDecimal calcularCobroPorDias(Integer cilindraje, double cantidadHorasParqueado, Integer valorDia, Integer valorHora) {
		BigDecimal valorCobrado = BigDecimal.ZERO;
		
		if (cantidadHorasParqueado > 24) {
		
			BigDecimal cantidadDiasTotales =  BigDecimal.valueOf((cantidadHorasParqueado / 24));
			Integer parteEnteraDiasTotales = cantidadDiasTotales.intValue();
			BigDecimal parteFraccionDiasTotales = cantidadDiasTotales.remainder(BigDecimal.ONE);
			
			valorCobrado =  BigDecimal.valueOf( parteEnteraDiasTotales.longValue() * valorDia.longValue());
			
			double horasRestantesPorCobrar = parteFraccionDiasTotales.multiply(new BigDecimal(24)).doubleValue();
			
			valorCobrado = valorCobrado.add( BigDecimal.valueOf(horasRestantesPorCobrar * valorHora));
						
		}else {
			
			valorCobrado = valorCobrado.add(new BigDecimal(valorDia));
		}
		
		valorCobrado = valorCobrado.add(cobrarPorCilandraje(cilindraje));
		
		
		return valorCobrado;
	}

	private BigDecimal cobrarPorCilandraje(Integer cilindraje) {
		BigDecimal valorCobrado = BigDecimal.ZERO;
		if(cilindraje > MovimientoParqueaderoConstant.CILINDRAJE_CON_RECARGO_MOTOS) {
			valorCobrado = valorCobrado.add(BigDecimal.valueOf(MovimientoParqueaderoConstant.VALOR_RECARGO_CILINDRAJE_500_MOTO));
		}
		
		return valorCobrado;
	}
	


	@Override
	public MovimientoParqueaderoEntity obtenerRetiradoParqueadoPorPlaca(String placa) {
		
		
		return movimientoParqueaderoRepository.obtenerRetiradoParqueadoPorPlaca(placa);
		
	}
	





	



	

}

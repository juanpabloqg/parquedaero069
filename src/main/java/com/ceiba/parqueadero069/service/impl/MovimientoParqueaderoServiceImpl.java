package com.ceiba.parqueadero069.service.impl;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
	public MovimientoParqueaderoEntity obtenerVehiculoParqueadoPorPlaca(String placa) {
		MovimientoParqueaderoEntity movimientoParqueaderoEntity = movimientoParqueaderoRepository.obtenerVehiculoParqueadoPorPlaca(placa);
		return movimientoParqueaderoEntity;
	}

	@Override
	public String retirarVehiculo(String placa, LocalDateTime fechaRetiro) throws MovimientoParqueaderoException {
		
		MovimientoParqueaderoEntity movimientoParqueaderoEntity = Optional
				.ofNullable(obtenerVehiculoParqueadoPorPlaca(placa))
				.orElseThrow(() -> new MovimientoParqueaderoException(MovimientoParqueaderoConstant.MENSAJE_ERRRO_VEHICULO_NO_EXISTE));
		
		MovimientoParqueadero movimientoParqueadero = calcularCobroVehiculo(movimientoParqueaderoBuilder
															.convertParqueoEntity2Parqueo(movimientoParqueaderoEntity),fechaRetiro);
		
		movimientoParqueaderoEntity.setEstado( movimientoParqueadero.getEstado());
		movimientoParqueaderoEntity.setFechaRetiro(movimientoParqueadero.getFechaRetiro());
		movimientoParqueaderoEntity.setValorCobrado(movimientoParqueadero.getValorCobrado());
		
		movimientoParqueaderoRepository.save(movimientoParqueaderoEntity);
		
		return MovimientoParqueaderoConstant.MENSAJE_RETIRO_VEHICULO_EXITOSO;
		
	}



	@Override
	public void verificarDisponibilidadPorInicioLetrasPlaca(MovimientoParqueadero movimientoParqueadero) {

		if (movimientoParqueadero.getVehiculo().getPlaca().charAt(0) == 'A') {

			if (!movimientoParqueadero.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
				if (!movimientoParqueadero.getFechaIngreso().getDayOfWeek().equals(DayOfWeek.MONDAY)) {
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
	
	
//	private double calcularDiasParqueado(MovimientoParqueadero movimientoParqueadero) {
//
//		return (double)(movimientoParqueadero.getFechaIngreso()
//				.until(LocalDateTime.now(), ChronoUnit.MINUTES))/60/24;
//	}
//	
//	private double calcularHorasParqueado(MovimientoParqueadero movimientoParqueadero) {
//		
//		
//
//		return (double)(movimientoParqueadero.getFechaIngreso()
//				.until(LocalDateTime.now(), ChronoUnit.MINUTES))/60;
//	}
	
	private MovimientoParqueadero calcularCobroVehiculo(MovimientoParqueadero movimientoParqueadero, LocalDateTime fechaRetiro) {
		
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(MovimientoParqueaderoConstant.FORMATO_FECHA);
		
		Duration duration = Duration.between(movimientoParqueadero.getFechaIngreso(), fechaRetiro);
		double cantidadHorasParqueado = (((double)duration.toMinutes())/60);		
		
		if (cantidadHorasParqueado > MovimientoParqueaderoConstant.CANTIDAD_HORAS_LIMITE_VEHICULO) {
			
			cobrarPorDias(movimientoParqueadero, fechaRetiro, cantidadHorasParqueado);			
			
		}else {
			
			cobrarPorHoras(movimientoParqueadero, fechaRetiro, cantidadHorasParqueado);
			
		}
		
		return movimientoParqueadero;
		
	}



	private void cobrarPorHoras(MovimientoParqueadero movimientoParqueadero, LocalDateTime fechaRetiro, double cantidadHorasParqueado) {
		
		if(movimientoParqueadero.getVehiculo().getTipoVehiculo().equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO)) {
			
			BigDecimal valorCobrado = new BigDecimal((cantidadHorasParqueado) * MovimientoParqueaderoConstant.VALOR_CARRO_HORA);
			
			movimientoParqueadero.setValorCobrado(valorCobrado);
			movimientoParqueadero.setFechaRetiro(fechaRetiro);
			movimientoParqueadero.setEstado(MovimientoParqueaderoConstant.ESTADO_RETIRADO);
			
		}else if(movimientoParqueadero.getVehiculo().getTipoVehiculo().equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO)){
			
			BigDecimal valorCobrado = new BigDecimal((cantidadHorasParqueado) * MovimientoParqueaderoConstant.VALOR_CARRO_HORA);
			
			movimientoParqueadero.setValorCobrado(cobrarVehiculoTipoMotoPorHoras(movimientoParqueadero,valorCobrado));
			movimientoParqueadero.setFechaRetiro(fechaRetiro);
			movimientoParqueadero.setEstado(MovimientoParqueaderoConstant.ESTADO_RETIRADO);
			
		}
		
	}

	private void cobrarPorDias(MovimientoParqueadero movimientoParqueadero, LocalDateTime fechaRetiro, double cantidadHorasParqueado) {
		if(movimientoParqueadero.getVehiculo().getTipoVehiculo().equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_CARRO)) {
			if (cantidadHorasParqueado > 24) {
				BigDecimal cantidadDiasTotales = new BigDecimal((cantidadHorasParqueado / 24));
				BigInteger parteEnteraDiasTotales = cantidadDiasTotales.toBigInteger();
				BigDecimal parteFraccionDiasTotales = cantidadDiasTotales.remainder(BigDecimal.ONE);
				
				BigDecimal valorCobrado = new BigDecimal(parteEnteraDiasTotales.intValue() * MovimientoParqueaderoConstant.VALOR_DIA_CARRO);
				
				double horasRestantesPorCobrar = parteFraccionDiasTotales.multiply(new BigDecimal(24)).doubleValue();
				
				valorCobrado = valorCobrado.add(new BigDecimal(horasRestantesPorCobrar * MovimientoParqueaderoConstant.VALOR_CARRO_HORA));
						
				
				movimientoParqueadero.setValorCobrado(valorCobrado);
				
			}else {
				
				movimientoParqueadero.setValorCobrado(new BigDecimal(MovimientoParqueaderoConstant.VALOR_DIA_CARRO));
			}
			
			movimientoParqueadero.setFechaRetiro(fechaRetiro);
			movimientoParqueadero.setEstado(MovimientoParqueaderoConstant.ESTADO_RETIRADO);
			
		}else if(movimientoParqueadero.getVehiculo().getTipoVehiculo().equals(MovimientoParqueaderoConstant.TIPO_VEHICULO_MOTO)){
			
			if (cantidadHorasParqueado > 24) {
			
				BigDecimal cantidadDiasTotales = new BigDecimal((cantidadHorasParqueado / 24));
				BigInteger parteEnteraDiasTotales = cantidadDiasTotales.toBigInteger();
				BigDecimal parteFraccionDiasTotales = cantidadDiasTotales.remainder(BigDecimal.ONE);
				
				BigDecimal valorCobrado = new BigDecimal(parteEnteraDiasTotales.intValue() * MovimientoParqueaderoConstant.VALOR_DIA_MOTO);
				
				double horasRestantesPorCobrar = parteFraccionDiasTotales.multiply(new BigDecimal(24)).doubleValue();
				
				valorCobrado = valorCobrado.add(new BigDecimal(horasRestantesPorCobrar * MovimientoParqueaderoConstant.VALOR_MOTO_HORA));
				
				movimientoParqueadero.setValorCobrado(cobrarVehiculoTipoMotoPorDias(movimientoParqueadero, valorCobrado));
			}else {
				
				movimientoParqueadero.setValorCobrado(new BigDecimal(MovimientoParqueaderoConstant.VALOR_DIA_MOTO));
			}
			
			if (movimientoParqueadero.getVehiculo().getCilindraje() > MovimientoParqueaderoConstant.CILINDRAJE_CON_RECARGO_MOTOS) {
				BigDecimal valorConRecargo = movimientoParqueadero.getValorCobrado();
				valorConRecargo = valorConRecargo.add(new BigDecimal(MovimientoParqueaderoConstant.VALOR_RECARGO_CILINDRAJE_500_MOTO));
				movimientoParqueadero.setValorCobrado(valorConRecargo);
			}
			movimientoParqueadero.setFechaRetiro(fechaRetiro);
			movimientoParqueadero.setEstado(MovimientoParqueaderoConstant.ESTADO_RETIRADO);
			
		}
	}

	private BigDecimal cobrarVehiculoTipoMotoPorDias(MovimientoParqueadero movimientoParqueadero, BigDecimal valorCobrado) {
		
		if(movimientoParqueadero.getVehiculo().getCilindraje() > MovimientoParqueaderoConstant.CILINDRAJE_CON_RECARGO_MOTOS) {
			
			valorCobrado.add(BigDecimal.valueOf(MovimientoParqueaderoConstant.VALOR_RECARGO_CILINDRAJE_500_MOTO));
			
		}
		
		return valorCobrado;
	}
	
	private BigDecimal cobrarVehiculoTipoMotoPorHoras(MovimientoParqueadero movimientoParqueadero, BigDecimal valorCobrado) {
		
		if(movimientoParqueadero.getVehiculo().getCilindraje() > MovimientoParqueaderoConstant.CILINDRAJE_CON_RECARGO_MOTOS) {
			
			valorCobrado.add(BigDecimal.valueOf(MovimientoParqueaderoConstant.VALOR_RECARGO_CILINDRAJE_500_MOTO));
			
		}
		
		return valorCobrado;
	}

	@Override
	public MovimientoParqueaderoEntity obtenerRetiradoParqueadoPorPlaca(String placa) {
		
		MovimientoParqueaderoEntity movimientoParqueaderoEntity = movimientoParqueaderoRepository.obtenerRetiradoParqueadoPorPlaca(placa);
		return movimientoParqueaderoEntity;
		
	}
	



//	private BigDecimal cobrarVehiculoTipoCarroPorDias(MovimientoParqueadero movimientoParqueadero, Integer valor) {
//		
//		double cantidadDiasParquedo = calcularDiasParqueado(movimientoParqueadero);
//		
//		return new BigDecimal(valor * cantidadDiasParquedo);
//		
//	}
//	
//	private BigDecimal cobrarVehiculoTipoCarroPorHoras(MovimientoParqueadero movimientoParqueadero,
//			Integer valorCarroHora) {
//			
//		
//		double cantidadHorasParqueo = calcularHorasParqueado(movimientoParqueadero);
//		
//		return new BigDecimal(valorCarroHora * cantidadHorasParqueo);
//	}


	



	

}

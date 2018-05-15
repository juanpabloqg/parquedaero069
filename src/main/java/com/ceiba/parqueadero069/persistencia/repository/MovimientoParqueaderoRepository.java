package com.ceiba.parqueadero069.persistencia.repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero069.persistencia.entity.MovimientoParqueaderoEntity;


@Repository("movimientoParqueaderoRepository")
public interface MovimientoParqueaderoRepository extends JpaRepository<MovimientoParqueaderoEntity, Serializable> {
	
	
	@Query("SELECT COUNT(m) FROM movimiento_parqueadero m WHERE m.vehiculoEntity.tipoVehiculo= :tipoVehiculo")
	public Integer countVehiculos(@Param("tipoVehiculo") String tipoVehiculo);
	
	@Query("SELECT m FROM movimiento_parqueadero m WHERE m.vehiculoEntity.placa = :placa and m.estado = 'INGRESADO'")
	public MovimientoParqueaderoEntity obtenerVehiculoParqueadoPorPlaca(@Param("placa") String placa);
	
	@Query("SELECT m FROM movimiento_parqueadero m WHERE m.vehiculoEntity.placa = :placa and m.estado = 'RETIRADO'")
	public MovimientoParqueaderoEntity obtenerRetiradoParqueadoPorPlaca(@Param("placa") String placa);
	
	@Modifying()
	@Query("UPDATE movimiento_parqueadero m  SET m.estado = :estado, m.fechaRetiro = :fechaRetiro, m.valorCobrado = :valorCobrado WHERE m.idParqueo = :idParqueo")
	public void actualizarMovimiento(@Param("estado") String estado, @Param("fechaRetiro") LocalDateTime fechaRetiro,
			@Param("valorCobrado") BigDecimal valorCobrado, @Param("idParqueo") Integer idParqueo);
	
	
	
	

}

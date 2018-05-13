package com.ceiba.parqueadero069.persistencia.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero069.persistencia.entity.MovimientoParqueaderoEntity;


@Repository("movimientoParqueaderoRepository")
public interface MovimientoParqueaderoRepository extends JpaRepository<MovimientoParqueaderoEntity, Serializable> {
	
	
	@Query("SELECT COUNT(m) FROM movimiento_parqueadero m WHERE m.vehiculoEntity.tipoVehiculo= :tipoVehiculo")
	public Integer countVehiculos(@Param("tipoVehiculo") String tipoVehiculo);
	
	@Query("SELECT m FROM movimiento_parqueadero m WHERE m.vehiculoEntity.placa = :placa")
	public MovimientoParqueaderoEntity obtenerVehiculoParqueadoPorPlaca(@Param("placa") String placa);
	
	
	

}

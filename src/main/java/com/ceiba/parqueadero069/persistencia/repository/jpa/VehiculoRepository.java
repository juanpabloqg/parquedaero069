package com.ceiba.parqueadero069.persistencia.repository.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero069.domain.Vehiculo;

@Repository("vehiculoRepository")
public interface VehiculoRepository extends JpaRepository<Vehiculo, Serializable> {

}

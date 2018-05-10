package com.ceiba.parqueadero069.persistencia.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero069.persistencia.entity.VehiculoEntity;

@Repository("vehiculoRepository")
public interface VehiculoRepository extends JpaRepository<VehiculoEntity, Serializable> {

}

package com.ceiba.parqueadero069.persistencia.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero069.persistencia.entity.ParqueoEntity;

@Repository("parqueoRepository")
public interface ParqueoRepository extends JpaRepository<ParqueoEntity, Serializable> {
	
	

}

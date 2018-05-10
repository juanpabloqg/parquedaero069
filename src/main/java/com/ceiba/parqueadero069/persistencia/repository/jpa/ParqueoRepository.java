package com.ceiba.parqueadero069.persistencia.repository.jpa;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ceiba.parqueadero069.domain.Parqueo;

@Repository("parqueoRepository")
public interface ParqueoRepository extends JpaRepository<Parqueo, Serializable> {
	
	

}

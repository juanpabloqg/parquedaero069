package com.ceiba.parqueadero069.service;

import org.springframework.stereotype.Service;

import com.ceiba.parqueadero069.domain.Parqueo;

@Service("parqueoService")
public class ParqueoService {
	
	public String ingresarCarro (Parqueo parqueo) {
		
		return "Carro ingresado exitosamente";
	}

}

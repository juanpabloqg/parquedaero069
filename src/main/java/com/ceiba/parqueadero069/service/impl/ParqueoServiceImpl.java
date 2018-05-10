package com.ceiba.parqueadero069.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ceiba.parqueadero069.domain.Parqueo;
import com.ceiba.parqueadero069.service.ParqueoService;

@Service("parqueoService")
public class ParqueoServiceImpl implements ParqueoService  {
	
	public String ingresarCarro (Parqueo parqueo) {
		
		return "Carro ingresado exitosamente";
	}

	@Override
	public Parqueo addParqueo(Parqueo parqueo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Parqueo> listAllParqueo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Parqueo findByPlaca(String placa) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeParqueo(Parqueo parqueo) {
		// TODO Auto-generated method stub
		
	}

}

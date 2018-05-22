package com.ceiba.parqueadero069.constantes;

public class MovimientoParqueaderoConstant {
	
	
	
	private MovimientoParqueaderoConstant() {
		super();
		
	}
	public static final String TIPO_VEHICULO_CARRO = "CARRO";
	public static final String TIPO_VEHICULO_MOTO = "MOTO";
	public static final String PLACA_CARRO = "HMV545";
	public static final String PLACA_MOTO = "HLV54D";
	public static final String PLACA_CARRO_INICA_CON_A = "AAA545";
	public static final String PLACA_MOTO_INICA_CON_A = "AAA54D";
	public static final String ESTADO_INGRESADO = "INGRESADO";
	public static final String ESTADO_RETIRADO = "RETIRADO";
	public static final Integer CILINDRAJE_CON_RECARGO_MOTOS = 500;
	public static final Integer VALOR_RECARGO_CILINDRAJE_500_MOTO = 2000;
	public static final long CANTIDAD_HORAS_LIMITE_VEHICULO = 9;
	
	
	
	public static final String FORMATO_FECHA = "yyyy-MM-dd HH:mm:ss";
	public static final String FECHA_INGRESO = "2018-05-09 08:00:00";
	public static final String FECHA_RETIRO = "2018-05-09 10:30:00";
	public static final String FECHA_RETIRO_1DIA_3HORAS = "2018-05-10 11:00:00";
	public static final String FECHA_RETIRO_10_HORAS = "2018-05-09 18:00:00";
	public static final String FECHA_INGRESO_DOMINGO = "2018-05-06 08:00:00";
	public static final String FECHA_INGRESO_LUNES = "2018-05-07 08:00:00";
	public static final String MENSAJE_INGRESO_EXITOSO = "Vehiculo ingresado exitosamente";
	public static final String MENSAJE_NO_DISPONIBILIDAD_PARQUEO = "No hay mas capacidad en el paqueadero";
	public static final String MENSAJE_RETIRO_VEHICULO_EXITOSO = "Vehiculo retirado exitosamente";
	public static final String MENSAJE_ERROR_RETIRAR_VEHICULO = "Error al retirar el vehiculo";
	public static final String MENSAJE_ERROR_INGRESO_VEHICULO_PLACA_INICIA_A = "Error al ingresar el vehiculo, en dia no permitido";
	
	public static final String MENSAJE_ERRRO_VEHICULO_NO_EXISTE = "Vehiculo con la placa digitada no existe";
	public static final String MENSAJE_ERRRO_VEHICULO_YA_EXISTE = "Vehiculo con la placa digitada ya existe";
	
	public static final Integer VALOR_CARRO_HORA = 1000;
	public static final Integer VALOR_MOTO_HORA = 500;
	public static final Integer VALOR_DIA_CARRO = 8000;
	public static final Integer VALOR_DIA_MOTO = 4000;
	public static final Integer CAPACIDAD_MAXIMA_CARRO = 20;
	public static final Integer CAPACIDAD_MAXIMA_MOTOS = 10;

}

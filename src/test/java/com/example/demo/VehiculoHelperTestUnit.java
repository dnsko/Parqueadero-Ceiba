package com.example.demo;

import com.example.demo.controladores.MainController;
import com.example.demo.entidades.Vehiculo;
import com.example.demo.enums.EstadoVehiculo;
import com.example.demo.enums.TipoVehiculo;
import com.example.demo.helpers.VehiculoHelper;
import com.example.demo.persistence.PersistenciaVehiculo;
import com.example.demo.vo.IngresoVehiculo;
import com.example.demo.vo.PlacaVehiculo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehiculoHelperTestUnit {

	private static final Logger LOGGER = LoggerFactory.getLogger(VehiculoHelper.class);
	@Autowired
	private VehiculoHelper vehiculoHelper;
	@Autowired
	private PersistenciaVehiculo persistenciaVehiculo;

	@Before
	public void generateData() {

	}
	@Test
	public void calculoPrecioTotalCarroTest() {
		Vehiculo vehiculo=new Vehiculo();
		try{
		vehiculo.setEstadoVehiculo(EstadoVehiculo.ADENTRO);
		vehiculo.setTipoVehiculo(TipoVehiculo.CARRO);
		vehiculo.setPlacas("uni321");
		String ingreso = "02/02/2019 10:00:00";
		Date ingresoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(ingreso);
		String salida = "03/02/2019 13:00:00";
		Date salidaFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(salida);
		vehiculo.setMomentoSalida(salidaFecha);
		vehiculo.setMomentoIngreso(ingresoFecha);
		}catch (Exception e){
			LOGGER.error("Error en la creacion de data previo a la ejecucion de la prueba calculoPrecioTotalCarroTest");
		}
		vehiculoHelper.calculoPrecioTtotal(vehiculo,8000L,1000L);
		long result=vehiculo.getValorParqueadero();
		assertEquals(11000L,result);
	}

	@Test
	public void calculoPrecioTotalMotoTest(){
		Vehiculo vehiculo=new Vehiculo();
		try{
			vehiculo.setEstadoVehiculo(EstadoVehiculo.ADENTRO);
			vehiculo.setTipoVehiculo(TipoVehiculo.MOTO);
			vehiculo.setPlacas("MOT321");
			String ingreso = "02/02/2019 07:00:00";
			Date ingresoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(ingreso);
			String salida = "02/02/2019 17:00:00";
			Date salidaFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(salida);
			vehiculo.setCilindraje(650);
			vehiculo.setMomentoSalida(salidaFecha);
			vehiculo.setMomentoIngreso(ingresoFecha);
		}catch (Exception e){
			LOGGER.error("Error en la creacion de data previo a la ejecucion de la prueba calculoPrecioTotalCarroTest");
		}
		vehiculoHelper.calculoPrecioTtotal(vehiculo,4000L,500L);
		long result=vehiculo.getValorParqueadero();
		assertEquals(6000L,result);
	}




}


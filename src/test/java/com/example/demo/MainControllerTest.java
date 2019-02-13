package com.example.demo;

import com.example.demo.controladores.MainController;
import com.example.demo.entidades.Vehiculo;
import com.example.demo.enums.EstadoVehiculo;
import com.example.demo.enums.TipoVehiculo;
import com.example.demo.persistence.PersistenciaVehiculo;
import com.example.demo.vo.IngresoVehiculo;
import com.example.demo.vo.PlacaVehiculo;
import static org.junit.Assert.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainControllerTest {

	Vehiculo carro;
	Vehiculo moto;

	@Autowired
	private MainController mainController;
	@Autowired
	private PersistenciaVehiculo persistenciaVehiculo;

	@Before
	public void generateData() {
		try {
			this.carro = new Vehiculo();
			this.carro.setPlacas("car123");
			String ingreso = "02/02/2019 23:37:50";
			Date ingresoFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(ingreso);
			String salida = "04/02/2019 07:37:50";
			Date salidaFecha = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(salida);
			this.carro.setMomentoIngreso(ingresoFecha);
			this.carro.setMomentoSalida(salidaFecha);
			this.carro.setEstadoVehiculo(EstadoVehiculo.ADENTRO);
			this.carro.setTipoVehiculo(TipoVehiculo.CARRO);
			this.carro.setAdentroParqueadero(true);
			persistenciaVehiculo.save(this.carro);
		}catch (Exception e){
			System.out.println("Error al crear la data previa para pruebas" + e);
		}
	}
	@Test
	public void ingresoCarroSuccess() {
		IngresoVehiculo ingresoVehiculo = new IngresoVehiculo();
		ingresoVehiculo.setPlacas("pru123");
		ingresoVehiculo.setTipoVehiculo(TipoVehiculo.CARRO);
		String response=mainController.ingresoVehiculo(ingresoVehiculo);
		assertEquals("Vehiculo de placas "+ingresoVehiculo.getPlacas() + " ingresa con fecha " + new Date(),response);
	}
	@Test
	public void ingresoCarroRepeat() {
		IngresoVehiculo ingresoVehiculo = new IngresoVehiculo();
		ingresoVehiculo.setPlacas("car123");
		ingresoVehiculo.setTipoVehiculo(TipoVehiculo.CARRO);
		String response=mainController.ingresoVehiculo(ingresoVehiculo);
		assertEquals("Un vehiculo de las mismas placas ya se encuentra adentro del parqueadero",response);
	}
	@Test
	public void retiroCarroSuccess() {
		PlacaVehiculo placaVehiculo = new PlacaVehiculo();
		placaVehiculo.setPlacas("car123");
		mainController.retiroVehiculo(placaVehiculo);
		List<Vehiculo> vehiculoList=persistenciaVehiculo.obtenerVehiculoPorPlacas(EstadoVehiculo.ADENTRO,"car123");
		assertEquals(0,vehiculoList.size());
	}



}


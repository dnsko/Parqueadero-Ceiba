package com.example.demo.interfaces;

import java.util.List;

import com.example.demo.entidades.Vehiculo;
import com.example.demo.enums.EstadoVehiculo;

public interface InterfacePersistenceVehiculo {
	/**
	 * Metodo para obtener vehiculos por las placas y el estado en el que se encuentra
	 * @return vehiculo consultado
	 */
	List<Vehiculo> obtenerVehiculoPorPlacas(EstadoVehiculo estadoVehiculo,String placa);
}

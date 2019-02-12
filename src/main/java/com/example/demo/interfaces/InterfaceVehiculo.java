package com.example.demo.interfaces;

import com.example.demo.vo.IngresoVehiculo;
import com.example.demo.vo.PlacaVehiculo;

public interface InterfaceVehiculo {
	
	String retiroVehiculo(PlacaVehiculo body);
	String ingresoVehiculo(IngresoVehiculo body);
}

package com.example.demo.controladores;

import com.example.demo.vo.IngresoVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.interfaces.InterfaceVehiculo;
import com.example.demo.vo.PlacaVehiculo;



@RestController
@CrossOrigin("*")
public class MainController {

	@Autowired
	private InterfaceVehiculo interfaceVehiculo;

	@RequestMapping(value="/retiroVehiculo", method=RequestMethod.POST)
	public String retiroVehiculo(@RequestBody PlacaVehiculo body) {
		return interfaceVehiculo.retiroVehiculo(body);
	}
	@RequestMapping(value="/ingresoVehiculo", method=RequestMethod.POST)
	public String ingresoVehiculo(@RequestBody IngresoVehiculo body) {
		return interfaceVehiculo.ingresoVehiculo(body);
	}
}

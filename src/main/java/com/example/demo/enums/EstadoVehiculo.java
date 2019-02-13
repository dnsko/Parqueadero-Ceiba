package com.example.demo.enums;

public enum EstadoVehiculo {

	ADENTRO,
	AFUERA;

    private String estadoDeVehiculo;

    public void estadoVehiculoEnum(String estadoVehiculo){
        this.estadoDeVehiculo = estadoVehiculo;
    }

    public String getEstadoDeVehiculo() {
        return estadoDeVehiculo;
    }
}

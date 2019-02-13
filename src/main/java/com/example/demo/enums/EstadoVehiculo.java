package com.example.demo.enums;

public enum EstadoVehiculo {

	ADENTRO("adentro"),
	AFUERA("afuera");

    private String estadoDeVehiculo;


    private EstadoVehiculo(String estadoDeVehiculo) {

    }

    public void estadoVehiculoEnum(String estadoVehiculo){
        this.estadoDeVehiculo = estadoVehiculo;
    }

    public String getEstadoDeVehiculo() {
        return estadoDeVehiculo;
    }
}

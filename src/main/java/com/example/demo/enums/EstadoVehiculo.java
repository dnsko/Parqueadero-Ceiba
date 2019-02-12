package com.example.demo.enums;

public enum EstadoVehiculo {

	ADENTRO("adentro"),
	AFUERA("afuera");

    private String estadoVehiculo;


    private EstadoVehiculo(String tipoVehiculo) {
    	
    }
    
    public void EstadoVehiculoEnum(String tipoVehiculo){
        this.estadoVehiculo = estadoVehiculo;
    }

    public String getEstadoVehiculo() {
        return estadoVehiculo;
    }
}

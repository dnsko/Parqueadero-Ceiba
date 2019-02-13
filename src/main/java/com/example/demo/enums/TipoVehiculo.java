package com.example.demo.enums;

public enum TipoVehiculo {
	
	CARRO("Carro"),
	MOTO("Moto");

    private String tipoDeVehiculo;


    private TipoVehiculo(String tipoDeVehiculo) {

    }

    public void tipoVehiculoEnum(String tipoVehiculo){
        this.tipoDeVehiculo = tipoVehiculo;
    }

    public String getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }
}

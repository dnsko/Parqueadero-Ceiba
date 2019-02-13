package com.example.demo.enums;

public enum TipoVehiculo {
	
	CARRO,
	MOTO;

    private String tipoDeVehiculo;

    public void tipoVehiculoEnum(String tipoVehiculo){
        this.tipoDeVehiculo = tipoVehiculo;
    }

    public String getTipoDeVehiculo() {
        return tipoDeVehiculo;
    }
}

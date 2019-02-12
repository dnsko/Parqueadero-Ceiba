package com.example.demo.enums;

public enum TipoVehiculo {
	
	CARRO("Carro"),
	MOTO("Moto");

    private String tipoVehiculo;

    
    private TipoVehiculo(String tipoVehiculo) {
    	
    }
    
    public void TipoVehiculoEnum(String tipoVehiculo){
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }
}

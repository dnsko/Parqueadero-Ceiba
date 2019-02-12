package com.example.demo.enums;

public enum TarifaPrecios {

	MOTO_HORA("500"),
	MOTO_DIA("4000"),
    CARRO_HORA("1000"),
    CARRO_DIA("8000");

    private String tarifaPrecios;


    private TarifaPrecios(String tarifaPrecios) {
    	
    }
    
    public void tarifaPreciosEnum(String tipoVehiculo){
        this.tarifaPrecios = tarifaPrecios;
    }

    public String getTarifaPrecios() {
        return tarifaPrecios;
    }
}
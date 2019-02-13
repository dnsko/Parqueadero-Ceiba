package com.example.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.enums.EstadoVehiculo;
import com.example.demo.enums.TipoVehiculo;

import java.util.Date;

@Entity
@Table(name="Vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private TipoVehiculo tipoVehiculo;

    private Boolean adentroParqueadero;

    private Date momentoIngreso;

    private Date momentoSalida;

    private String placas;

    private Long valorParqueadero;

    private String cilindraje;

    private EstadoVehiculo estadoVehiculo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public Boolean getAdentroParqueadero() {
        return adentroParqueadero;
    }

    public void setAdentroParqueadero(Boolean adentroParqueadero) {
        this.adentroParqueadero = adentroParqueadero;
    }

    public Date getMomentoIngreso() {
        return momentoIngreso;
    }

    public void setMomentoIngreso(Date momentoIngreso) {
        this.momentoIngreso = momentoIngreso;
    }

    public Date getMomentoSalida() {
        return momentoSalida;
    }

    public void setMomentoSalida(Date momentoSalida) {
        this.momentoSalida = momentoSalida;
    }

    public String getPlacas() {
        return placas;
    }

    public void setPlacas(String placas) {
        this.placas = placas;
    }

    public Long getValorParqueadero() {
        return valorParqueadero;
    }

    public void setValorParqueadero(Long valorParqueadero) {
        this.valorParqueadero = valorParqueadero;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }

    public EstadoVehiculo getEstadoVehiculo() {
        return estadoVehiculo;
    }

    public void setEstadoVehiculo(EstadoVehiculo estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

}

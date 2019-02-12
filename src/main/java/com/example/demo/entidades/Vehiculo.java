package com.example.demo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.enums.EstadoVehiculo;
import com.example.demo.enums.TipoVehiculo;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

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

    /**
     * Metodo para calcular el valor del vehiculo
     * @return
     */
    public String calcularValorParqueadero(){
        try{
            Calendar calendar=Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            /*Validacion placas*/
            if(this.placas.toLowerCase().startsWith("a") && dayOfWeek==1 || dayOfWeek==7){
                return "Acceso no autorizado al vehiculo por placa";
            }else{
                if(this.tipoVehiculo.equals(TipoVehiculo.CARRO)){
                    return "try";
                }else{
                    return "try";
                }
            }

        }catch (Exception e){
            this.valorParqueadero=null;
            return null;
        }
    }

    /**
     *
     * @param precioHora precio hora
     * @param precioDia precio dia
     * @return
     */
    public String calcularValor(Double precioHora,Double precioDia,Double exedente){
        Date entrada=this.momentoIngreso;
        Date salida=this.momentoSalida;
        return "try";
    }
}

package com.example.demo.helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.example.demo.enums.TarifaPrecios;
import com.example.demo.vo.IngresoVehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Vehiculo;
import com.example.demo.enums.EstadoVehiculo;
import com.example.demo.enums.TipoVehiculo;
import com.example.demo.interfaces.InterfaceVehiculo;
import com.example.demo.persistence.PersistenciaVehiculo;
import com.example.demo.vo.PlacaVehiculo;

@Service
public class VehiculoHelper implements InterfaceVehiculo {
	
    @Autowired
    private PersistenciaVehiculo interfacePersistenceVehiculo;

    /**
     * Metodo para calcular el valor a pagar de un vehiculo
     * @param placaVO placa del vehiculo
     * @return retorna el valor o en su defecto al problema por el cual no se puede facturar el vehiculo
     */
    @Override
    public String retiroVehiculo(PlacaVehiculo placaVO) {
        try{
            Vehiculo vehiculo;
            try {
            	List<Vehiculo> vehiculoList = interfacePersistenceVehiculo.obtenerVehiculoPorPlacas(EstadoVehiculo.ADENTRO,placaVO.getPlacas());
            	vehiculo=vehiculoList.get(0);
            }catch(Exception e) {
            	return "No existe el veihiculo en el parqueadero";
            }
            vehiculo.setMomentoSalida(new Date());
            calculateValue(vehiculo);
            vehiculo.setEstadoVehiculo(EstadoVehiculo.AFUERA);
            return "Vehiculo de placas "+vehiculo.getPlacas()+" Retirado exitosamente en "+vehiculo.getMomentoSalida()+" con un valor a pagar de "+vehiculo.getValorParqueadero();
        }catch (Exception e){
            return "Error: "+e;
        }
    }

    /**
     * Metodo para dar ingreso al un vehiculo
     * @param ingresoVehiculo ingresoVehiculo
     * @return respuesta de exito del ingreso
     */
    @Override
    public String ingresoVehiculo(IngresoVehiculo ingresoVehiculo) {
        try{
            Calendar calendar=Calendar.getInstance();
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
            if(ingresoVehiculo.getPlacas().toLowerCase().startsWith("a") && dayOfWeek==1 || dayOfWeek==7){
                return "Acceso no autorizado al vehiculo por placa";
            }else{
                List<Vehiculo> vehiculoList = interfacePersistenceVehiculo.obtenerVehiculoPorPlacas(EstadoVehiculo.ADENTRO,ingresoVehiculo.getPlacas());
                if(!vehiculoList.isEmpty()){
                    return "Un vehiculo de las mismas placas ya se encuentra adentro del parqueadero";
                }else {
                    Vehiculo vehiculo=new Vehiculo();
                    vehiculo.setMomentoIngreso(new Date());
                    vehiculo.setAdentroParqueadero(true);
                    vehiculo.setCilindraje(ingresoVehiculo.getCilindraje());
                    vehiculo.setPlacas(ingresoVehiculo.getPlacas());
                    vehiculo.setEstadoVehiculo(EstadoVehiculo.ADENTRO);
                    vehiculo.setTipoVehiculo(ingresoVehiculo.getTipoVehiculo());
                    interfacePersistenceVehiculo.save(vehiculo);
                    return "Vehiculo de placas "+vehiculo.getPlacas() + " ingresa con fecha " + vehiculo.getMomentoIngreso();
                }
            }
        }catch (Exception e){
            return "Error al ingresar el vehiculo " + e;
        }
    }
    /**
     * Metodo para calcular el valor ya sea carro o moto
     * @param vehiculo entidad vehiculo a calcular
     */
   private void calculateValue(Vehiculo vehiculo) {

	   if(TipoVehiculo.CARRO.equals(vehiculo.getTipoVehiculo())){
           calculoPrecioTtotal(vehiculo, TarifaPrecios.CARRO_DIA,TarifaPrecios.CARRO_HORA);
       }else{
           calculoPrecioTtotal(vehiculo,TarifaPrecios.MOTO_DIA,TarifaPrecios.MOTO_HORA);
       }

   }

    /**
     * Metodo parta calcular el precio total
     * @param vehiculo vehiculo
     */
   private void calculoPrecioTtotal(Vehiculo vehiculo,TarifaPrecios dia,TarifaPrecios hora){
       try{
           Date initialDate=vehiculo.getMomentoIngreso();
           Date finalDate=vehiculo.getMomentoSalida();
           Long dateDif=initialDate.getTime()-finalDate.getTime();
           Long dateDifInHours=dateDif/(60*60*1000);
           Long totalDays=dateDifInHours/24;
           Long daysValue=totalDays*Long.parseLong(dia.getTarifaPrecios());
           Long totalHours=dateDifInHours%24;
           Long hoursValue;
           if(9<=totalHours){
               hoursValue=Long.parseLong(dia.getTarifaPrecios());
           }else {
               hoursValue=totalHours*Long.parseLong(hora.getTarifaPrecios());
           }
           vehiculo.setValorParqueadero(daysValue + hoursValue);
       }catch (Exception e){
           System.out.println(e);
           vehiculo.setValorParqueadero(null);
       }
   }
}

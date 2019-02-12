package com.example.demo.helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
            return calculateValue(vehiculo);
        }catch (Exception e){
            return null;
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
                    return "Vehiculo de placas "+vehiculo.getPlacas() + "ingresa con fecha" + vehiculo.getMomentoIngreso();
                }
            }
        }catch (Exception e){
            return "Error al ingresar el vehiculo";
        }
    }
    /**
     * Metodo para calcular el valor ya sea carro o moto
     * @param vehiculo entidad vehiculo a calcular
     * @return valor n string para retornar
     */
   private String calculateValue(Vehiculo vehiculo) {

	   if(TipoVehiculo.CARRO.equals(vehiculo.getTipoVehiculo())){
           calculoDiasYHoras(vehiculo);
       }else{
           calculoDiasYHoras(vehiculo);
       }
	   return"";
   }

    /**
     * Metodo parta calcular la cantidad de dias y horas para facturar
     * @param vehiculo vehiculo
     * @return lista de numeros en el cual se encuentran en la posicion 1 los dias y en la posicion 2 las horas
     */
   private List<Long> calculoDiasYHoras(Vehiculo vehiculo){
       try{
           List<Long> diasYHoras=new ArrayList<>();
           Date initialDate=vehiculo.getMomentoIngreso();
           Date finalDate=vehiculo.getMomentoSalida();
           Long dateDif=initialDate.getTime()-finalDate.getTime();
           Long dateDifInDays=dateDif/(24*60*60*1000);
           System.out.println(dateDifInDays);
           return diasYHoras;
       }catch (Exception e){
           System.out.println(e);
           return null;
       }
   }
}

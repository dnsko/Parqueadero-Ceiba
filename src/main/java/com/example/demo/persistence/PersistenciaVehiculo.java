package com.example.demo.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Vehiculo;
import com.example.demo.enums.EstadoVehiculo;

@Repository
public interface PersistenciaVehiculo extends CrudRepository<Vehiculo,Long> {

	@Query("SELECT vehiculo FROM Vehiculo vehiculo WHERE vehiculo.estadoVehiculo = :estadoVehiculo and vehiculo.placas = :placa")
	List<Vehiculo> obtenerVehiculoPorPlacas(@Param("estadoVehiculo") EstadoVehiculo estadoVehiculo,@Param("placa") String placa);
}

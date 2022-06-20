package com.evehicle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.evehicle.entity.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Integer> {

	List<VehicleEntity> findByVehicleName(String vehicleName);

	List<VehicleEntity> findByVehicleModel(String vehicleModel);

}

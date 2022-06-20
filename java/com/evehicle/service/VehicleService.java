package com.evehicle.service;

import java.util.List;

import com.evehicle.entity.VehicleEntity;

public interface VehicleService {

	VehicleEntity saveVehicle(VehicleEntity vehicle);

	VehicleEntity getVehicleById(int vehicleId);

	List<VehicleEntity> viewAllVehicles();

	List<VehicleEntity> selectVehicleByName(String vehicleName);

	List<VehicleEntity> selectVehicleByModel(String vehicleModel);

}

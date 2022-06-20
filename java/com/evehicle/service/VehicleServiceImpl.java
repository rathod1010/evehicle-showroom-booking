package com.evehicle.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evehicle.entity.VehicleEntity;
import com.evehicle.exception.VehicleNotFoundException;
import com.evehicle.repository.VehicleRepository;

@Service
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public VehicleEntity saveVehicle(VehicleEntity vehicle) {

		return vehicleRepository.save(vehicle);

	}

	@Override
	public VehicleEntity getVehicleById(int vehicleId) throws VehicleNotFoundException {

		Optional<VehicleEntity> optionalVehicle = vehicleRepository.findById(vehicleId);

		if (optionalVehicle.isEmpty()) {

			throw new VehicleNotFoundException("vehicle Not found with id: " + vehicleId);
		}

		return optionalVehicle.get();
	}

	@Override
	public List<VehicleEntity> viewAllVehicles() {

		return vehicleRepository.findAll();
	}

	@Override
	public List<VehicleEntity> selectVehicleByName(String vehicleName) {

		List<VehicleEntity> vehicle = vehicleRepository.findByVehicleName(vehicleName);

		if (vehicle.isEmpty()) {
			throw new VehicleNotFoundException("vehicle not found with name " + vehicleName);
		}

		return vehicle;
	}

	@Override
	public List<VehicleEntity> selectVehicleByModel(String vehicleModel) {

		List<VehicleEntity> vehicle = vehicleRepository.findByVehicleModel(vehicleModel);

		if (vehicle.isEmpty()) {
			throw new VehicleNotFoundException("vehicle not found with model " + vehicleModel);
		}
		return vehicle;
	}

}

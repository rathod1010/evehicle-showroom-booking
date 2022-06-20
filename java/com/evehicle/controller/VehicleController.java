package com.evehicle.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evehicle.entity.VehicleEntity;
import com.evehicle.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
@CrossOrigin(origins = "http://localhost:3000/")
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	@PostMapping("/save")
	public ResponseEntity<VehicleEntity> addProduct(@Valid @RequestBody VehicleEntity vehicle) {

		VehicleEntity newVehicle = vehicleService.saveVehicle(vehicle);
		
		return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
	}

	@GetMapping("/find/{vehicleId}")
	public ResponseEntity<Object> fetchVehicleById(@PathVariable("vehicleId") int vehicleId) {

		ResponseEntity<Object> responseEntity = null;
		VehicleEntity vehicle = vehicleService.getVehicleById(vehicleId);
		responseEntity = new ResponseEntity<>(vehicle, HttpStatus.OK);
		return responseEntity;

	}

	@GetMapping("/all")
	public List<VehicleEntity> fetchAllVehicles() {

		return vehicleService.viewAllVehicles();
	}

	@GetMapping("/byname/{vehicleName}")
	public List<VehicleEntity> fetchVehicleByName(@PathVariable("vehicleName") String vehicleName) {

		return vehicleService.selectVehicleByName(vehicleName);
	}

	@GetMapping("/bymodel/{vehicleModel}")
	public List<VehicleEntity> fetchVehicleByModel(@PathVariable("vehicleModel") String vehicleModel) {

		return vehicleService.selectVehicleByModel(vehicleModel);
	}
}

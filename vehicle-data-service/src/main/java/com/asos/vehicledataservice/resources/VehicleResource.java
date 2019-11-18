package com.asos.vehicledataservice.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asos.vehicledataservice.model.IVehicle;
import com.asos.vehicledataservice.services.VehicleService;

/**
 * @author rohan
 *
 */
@RestController
@RequestMapping("/vehicles")
public class VehicleResource {

	@Autowired
	VehicleService vehicleService;

	// The Service is up on localhost and port number 8081
	// Example URL:
	// http://localhost:8081/vehicles/all
	/**
	 * @return list of all vehicles
	 */
	@RequestMapping("/all")
	public List<IVehicle> getAllVehicleInfo() {
		return vehicleService.getAllVehicleInfo();

	}

	// The Service is up on localhost and port number 8081
	// Example URL:
	// http://localhost:8081/vehicles/getVehicle?vehicleId=1&vehicleType=van
	/**
	 * @param vehicleType
	 * @param fuelType
	 * @param ac
	 * @return Vehicle information object
	 */
	@RequestMapping("/getVehicle")
	public IVehicle getVehicle(@RequestParam(value = "vehicleType", required = true) String vehicleType,
			@RequestParam(value = "fuelType", defaultValue = "petrol") String fuelType,
			@RequestParam(value = "ac", defaultValue = "false") String ac) {
		return vehicleService.getVehicle(vehicleType, fuelType, ac);
	}
}

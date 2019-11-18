package com.asos.vehicledataservice.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asos.vehicledataservice.model.IVehicle;
import com.asos.vehicledataservice.enums.FuelType;
import com.asos.vehicledataservice.enums.VehicleType;
import com.asos.vehicledataservice.model.Bus;
import com.asos.vehicledataservice.model.Car;
import com.asos.vehicledataservice.model.Van;

/**
 * @author rohan
 *
 */
@Service
public class VehicleService {

	@Autowired
	@Qualifier("car")
	IVehicle car;

	@Autowired
	@Qualifier("bus")
	IVehicle bus;

	@Autowired
	@Qualifier("van")
	IVehicle van;

	/**
	 * @return all vehicle objects
	 */
	public List<IVehicle> getAllVehicleInfo() {
		return Arrays.asList(car, bus, van);

	}

	/**
	 * @param vehicleType
	 * @param fuelType
	 * @param ac
	 * @return Vehicle
	 */
	public IVehicle getVehicle(String vehicleType, String fuelType, String ac) {
		// The user selected fuel type will be considered. If fuel type is not provided
		// then the default will be used.
		FuelType selectedFuelType = null;
		if (FuelType.PETROL.toString().equalsIgnoreCase(fuelType)) {
			selectedFuelType = FuelType.PETROL;
		} else if (FuelType.DIESEL.toString().equalsIgnoreCase(fuelType)) {
			selectedFuelType = FuelType.DIESEL;
		}

		// For each vehicle type return the corresponding object.
		if (VehicleType.CAR.toString().equalsIgnoreCase(vehicleType)) {
			// Builder pattern is used to create car/van/bus object
			return new Car.CarBuilder().fuelType(selectedFuelType).ac(Boolean.parseBoolean(ac)).build();
		} else if (VehicleType.BUS.toString().equalsIgnoreCase(vehicleType)) {
			return new Bus.BusBuilder().ac(Boolean.parseBoolean(ac)).build();
		} else if (VehicleType.VAN.toString().equalsIgnoreCase(vehicleType)) {
			return new Van.VanBuilder().fuelType(selectedFuelType).ac(Boolean.parseBoolean(ac)).build();
		} else {
			return null;
		}
	}
}

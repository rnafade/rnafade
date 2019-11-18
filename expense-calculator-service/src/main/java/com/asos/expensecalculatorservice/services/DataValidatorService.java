package com.asos.expensecalculatorservice.services;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.Locations;
import com.asos.expensecalculatorservice.enums.VehicleType;

/**
 * @author rohan
 *
 */
@Service(value = "DataValidatorService")
public class DataValidatorService implements IDataValidatorService {

	/**
	 * This method will validate the input parameters of the request.
	 */
	public String validateRequest(String destination, String vehicleType, String fuelType, String ac) {
		String validationMessage = null;
		String ResultMessage = "";

		validationMessage = validateDestination(destination);
		if (validationMessage != null) {
			ResultMessage += validationMessage;
		}
		validationMessage = validateVehicleType(vehicleType);
		if (validationMessage != null) {
			ResultMessage += validationMessage;
		}
		validationMessage = validateFuelType(fuelType);
		if (validationMessage != null) {
			ResultMessage += validationMessage;
		}
		validationMessage = validateAc(ac);
		if (validationMessage != null) {
			ResultMessage += validationMessage;
		}
		return ResultMessage;
	}

	/**
	 * @param destination
	 * @return
	 */
	private String validateDestination(String destination) {
		String message = null;
		if (destination == null || destination.isEmpty()) {
			message = "Destination cannot be empty.";
			return message;
		}
		boolean isListed = Arrays.stream(Locations.values())
				.anyMatch((t) -> t.name().equals(destination.toUpperCase()));
		if (!isListed) {
			message = "Destination is not Listed.";
		}
		return message;
	}

	/**
	 * @param vehicleType
	 * @return
	 */
	private String validateVehicleType(String vehicleType) {
		String message = null;
		if (vehicleType == null || vehicleType.isEmpty()) {
			message = "VehicleType cannot be empty.";
			return message;
		}
		boolean isListed = Arrays.stream(VehicleType.values())
				.anyMatch((t) -> t.name().equals(vehicleType.toUpperCase()));
		if (!isListed) {
			message = "Vehicle is not Listed.";
		}
		return message;
	}

	/**
	 * @param fuelType
	 * @return
	 */
	private String validateFuelType(String fuelType) {
		String message = null;
		boolean isListed = Arrays.stream(FuelType.values()).anyMatch((t) -> t.name().equals(fuelType.toUpperCase()));
		if (!isListed) {
			message = "Invalid Fuel type.";
		}
		return message;
	}

	/**
	 * @param isAcRequired
	 * @return
	 */
	private String validateAc(String isAcRequired) {
		String message = null;
		if (isAcRequired != null && !isAcRequired.isEmpty()) {
			if (!(isAcRequired.equalsIgnoreCase("true") || isAcRequired.equalsIgnoreCase("false"))) {
				message = "Incorect value for Ac/NonAc.";
			}
		}
		return message;
	}

}

package com.asos.expensecalculatorservice.services;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.Locations;
import com.asos.expensecalculatorservice.enums.VehicleType;
import com.asos.expensecalculatorservice.exceptions.ServiceException;
import com.asos.expensecalculatorservice.exceptions.ValidationException;
import com.asos.expensecalculatorservice.model.IVehicle;

/**
 * @author rohan
 *
 */
@Service
public class ExpenseCalculatorService implements ExpenseCalculator {

	@Autowired
	@Qualifier("VehicleInfoService")
	IVehicleInfoService vehicleInfoService;

	@Autowired
	@Qualifier("DataValidatorService")
	IDataValidatorService dataValidatorService;

	@Autowired
	@Qualifier("DistanceCalculatorService")
	IDistanceCalculatorService distanceCalculatorService;

	// The standard rate is 15.0 Rs.
	private static final double STANDARD_RATE = 15.0d;

	/*
	 * This method will be called by the Controller, which will validate the
	 * request. If the request is valid then it will be considered fro further
	 * processing.
	 */
	public BigDecimal calculateTripExpense(String destination, String vehicleType, String fuelType, String ac,
			String numberOfPassengers) throws ServiceException, ValidationException {
		String validation = dataValidatorService.validateRequest(destination, vehicleType, fuelType, ac);
		if (!validation.isEmpty()) {
			throw new ValidationException(validation);
		}

		return calculateExpense(VehicleType.valueOf(vehicleType.toUpperCase()),
				FuelType.valueOf(fuelType.toUpperCase()), destination, Integer.parseInt(numberOfPassengers),
				Boolean.parseBoolean(ac));
	}

	/**
	 * This method is the implementation of the ExpenseCalculator interface provided
	 * in the problem statement.
	 */
	@Override
	public BigDecimal calculateExpense(VehicleType vehicleType, FuelType fuelType, String destination,
			Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired) throws ServiceException {
		// The vehicle information will be fetched from a rest service (microservice).
		// The required details about the Vehicle will be fetched, e.g. capacity and
		// fuel type etc. If the VehicleInfo rest service is down then the error will be
		// shown to the Client.
		IVehicle vehicle = vehicleInfoService.getVehicleInfo(vehicleType, fuelType, isAirConditioningRequired);
		if (vehicle == null) {
			throw new ServiceException("Vehicle information not available.");
		}
		// Calculate per KM rate of the Vehicle based on the conditions given in the
		// problem statement
		double perKMRate = calculatePerKMRate(vehicle, numberOfPeopleTravelling);
		// Calculate the distance between two locations. Pune is the Source here always.
		double distanceInKM = distanceCalculatorService.getDistance(Locations.PUNE,
				Locations.valueOf(destination.toUpperCase()));
		// Calculate expense using below formula
		double finalRate = perKMRate * distanceInKM;
		// Response should be sent in BigDecimal format with scale 2.
		return new BigDecimal(finalRate).setScale(2, RoundingMode.FLOOR);
	}

	/**
	 * @param vehicle
	 * @param numberOfPeopleTravelling
	 * @return per Km rate of the vehicle. Calculate per KM rate of the Vehicle
	 *         based on the conditions given in the problem statement
	 * 
	 */
	private double calculatePerKMRate(IVehicle vehicle, Integer numberOfPeopleTravelling) {
		double tripRate = STANDARD_RATE;
		double discountRate = 0d;

		// rate charges
		// Additional 2 Rs/Km charge is applicable for AC vehicles.
		if (vehicle.isAc()) {
			tripRate += 2.0d;
		}
		// Diesel vehicles cost a rupee less than standard rate.
		if (vehicle.getFuelType() == FuelType.DIESEL) {
			tripRate -= 1.0d;
		}

		// Please note every Vehicle has a maximum passenger capacity.
		// An additional charge of 1 Rs/Km/Person is imposed if number of passengers
		// exceeds the max limit of a vehicle. Here, the charge is calculated for excess
		// passengers only.
		int exceessPassengers = calculateExceessPassengers(vehicle.getCapacity(), numberOfPeopleTravelling);
		tripRate += exceessPassengers;

		// discount
		// 2% discount is applicable for bus on standard rate.
		if (vehicle.getVehicleType() == VehicleType.BUS) {
			discountRate = STANDARD_RATE * (0.02d);
		}
		tripRate -= discountRate;
		return tripRate;
	}

	/**
	 * @param capacity
	 * @param numberOfPeopleTravelling
	 * @return number of excess passengers
	 */
	private int calculateExceessPassengers(int capacity, int numberOfPeopleTravelling) {
		if (numberOfPeopleTravelling > capacity) {
			return numberOfPeopleTravelling - capacity;
		}
		return 0;
	}

}

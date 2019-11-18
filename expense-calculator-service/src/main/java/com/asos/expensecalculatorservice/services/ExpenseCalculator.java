package com.asos.expensecalculatorservice.services;

import java.math.BigDecimal;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;

public interface ExpenseCalculator {
	BigDecimal calculateExpense(VehicleType vehicleType, FuelType fuelType, String destination,
			Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired);
}

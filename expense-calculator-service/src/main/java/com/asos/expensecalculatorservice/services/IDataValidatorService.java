package com.asos.expensecalculatorservice.services;

public interface IDataValidatorService {

	public String validateRequest(String destination, String vehicleType, String fuelType, String ac);
}

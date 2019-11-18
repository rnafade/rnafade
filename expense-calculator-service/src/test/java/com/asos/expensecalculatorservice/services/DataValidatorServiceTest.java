package com.asos.expensecalculatorservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DataValidatorServiceTest {

	@Autowired
	IDataValidatorService dataValidatorService;

	@ParameterizedTest
	@CsvSource({ "PUNE,CAR,PETROL,true,''", "'',VAN,PETROL,true,Destination cannot be empty.",
			"INCORRECT_DESTINATION,BUS,DIESEL,true,Destination is not Listed.",
			"PUNE,'',PETROL,true,VehicleType cannot be empty.", "PUNE,OTHER_VEHICLE,PETROL,true,Vehicle is not Listed.",
			"PUNE,CAR,INCORRECT_FUEL,true,Invalid Fuel type.",
			"PUNE,CAR,PETROL,INCORRECT_AC,Incorect value for Ac/NonAc." })
	void testValidateRequest(String destination, String vehicleType, String fuelType, String ac, String expected) {
		String result = dataValidatorService.validateRequest(destination, vehicleType, fuelType, ac);
		assertEquals(result, expected);
	}

}

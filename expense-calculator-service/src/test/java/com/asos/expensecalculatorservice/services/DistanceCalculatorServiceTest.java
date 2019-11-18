package com.asos.expensecalculatorservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.asos.expensecalculatorservice.enums.Locations;

@SpringBootTest
class DistanceCalculatorServiceTest {

	@Autowired
	IDistanceCalculatorService distanceCalculatorService;

	@ParameterizedTest
	@CsvSource({ "PUNE,PUNE,0", "PUNE,MUMBAI,200", "PUNE,BANGALORE,1000", "PUNE,DELHI,2050", "PUNE,CHENNAI,1234.5" })
	void testGetVehicleInfo(Locations source, Locations destination, Double expected) {
		double result = distanceCalculatorService.getDistance(source, destination);
		assertEquals(result, expected.doubleValue());
	}

}

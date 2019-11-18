package com.asos.expensecalculatorservice.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.net.URL;

import org.junit.Before;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;
import com.asos.expensecalculatorservice.model.Car;
import com.asos.expensecalculatorservice.services.IDataValidatorService;
import com.asos.expensecalculatorservice.services.IDistanceCalculatorService;
import com.asos.expensecalculatorservice.services.IVehicleInfoService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ExpenseCalculatorResourceTest {

	// bind the above RANDOM_PORT
	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@MockBean
	IDataValidatorService dataValidatorService;

	@MockBean
	IVehicleInfoService vehicleInfoService;

	@MockBean
	IDistanceCalculatorService distanceCalculatorService;

	@Before
	public void setup() {
	}

	@ParameterizedTest
	@CsvSource({ "mumbai,car,true,petrol,5,5,3400.00", "chennai,bus,true,diesel,60,50,31726.64",
			"delhi,van,false,petrol,10,7,41000.00" })
	public void testCalculateTripExpense(String destination, String vehicleType, String ac, String fuelType,
			String numberOfPassengers, String capacity, String expected) throws Exception {
		Mockito.when(dataValidatorService.validateRequest(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),
				Mockito.anyString())).thenReturn("");
		Mockito.when(vehicleInfoService.getVehicleInfo(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(new Car(VehicleType.valueOf(vehicleType.toUpperCase()), Integer.parseInt(capacity),
						FuelType.valueOf(fuelType.toUpperCase()), true));
		double distance = 0d;
		if (destination.equalsIgnoreCase("mumbai")) {
			distance = 200d;
		} else if (destination.equalsIgnoreCase("chennai")) {
			distance = 1234.5d;
		} else if (destination.equalsIgnoreCase("delhi")) {
			distance = 2050d;
		}
		Mockito.when(distanceCalculatorService.getDistance(Mockito.any(), Mockito.any())).thenReturn(distance);
		ResponseEntity<BigDecimal> response = restTemplate.getForEntity(new URL("http://localhost:" + port
				+ "/ExpenseCalculator/trip?destination=" + destination + "&vehicleType=" + vehicleType + "&ac=" + ac
				+ "&fuelType=" + fuelType + "&numberOfPassengers=" + numberOfPassengers).toString(), BigDecimal.class);
		assertEquals(new BigDecimal(expected), response.getBody());

	}

}

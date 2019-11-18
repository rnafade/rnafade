package com.asos.expensecalculatorservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;
import com.asos.expensecalculatorservice.model.Bus;
import com.asos.expensecalculatorservice.model.Car;
import com.asos.expensecalculatorservice.model.IVehicle;
import com.asos.expensecalculatorservice.model.Van;

@SpringBootTest
class VehicleInfoServiceTest {

	@Autowired
	IVehicleInfoService vehicleInfoService;

	@MockBean
	RestTemplate restTemplate;

	@ParameterizedTest
	@CsvSource({ "CAR,PETROL,true", "VAN,PETROL,true", "BUS,DIESEL,true" })
	void testGetVehicleInfo(VehicleType vehicleType, FuelType fuelType, Boolean isAirConditioningRequired) {
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Car.class)))
				.thenReturn(new Car(VehicleType.CAR, 5, FuelType.PETROL, false));
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Bus.class)))
				.thenReturn(new Bus(VehicleType.BUS, 50, FuelType.DIESEL, true));
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.eq(Van.class)))
				.thenReturn(new Van(VehicleType.VAN, 7, FuelType.PETROL, false));
		IVehicle result = vehicleInfoService.getVehicleInfo(vehicleType, fuelType, isAirConditioningRequired);
		assertEquals(vehicleType, result.getVehicleType());
	}

}

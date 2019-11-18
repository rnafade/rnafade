package com.asos.expensecalculatorservice.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;
import com.asos.expensecalculatorservice.model.Bus;

@SpringBootTest
class ExpenseCalculatorServiceTest {

	@Autowired
	ExpenseCalculator expenseCalculatorService;

	@MockBean
	IVehicleInfoService vehicleInfoService;

	@ParameterizedTest
	@CsvSource({ "BUS,PETROL,MUMBAI,60,true,5140.00" })
	void testCalculateExpense(VehicleType vehicleType, FuelType fuelType, String destination,
			Integer numberOfPeopleTravelling, Boolean isAirConditioningRequired, String expected) {
		Mockito.when(vehicleInfoService.getVehicleInfo(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(new Bus(vehicleType, 50, FuelType.DIESEL, true));

		BigDecimal result = expenseCalculatorService.calculateExpense(vehicleType, fuelType, destination,
				numberOfPeopleTravelling, isAirConditioningRequired);
		assertEquals(result, new BigDecimal(expected));
	}

}

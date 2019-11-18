package com.asos.expensecalculatorservice.resource;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asos.expensecalculatorservice.exceptions.ServiceException;
import com.asos.expensecalculatorservice.exceptions.ValidationException;
import com.asos.expensecalculatorservice.services.ExpenseCalculatorService;

@RestController
@RequestMapping("/ExpenseCalculator")
public class ExpenseCalculatorResource {

	@Autowired
	ExpenseCalculatorService expenseCalculatorService;

	/*
	 * COMMENTS: This is a Rest service taking the required input fields and returns
	 * the final expenses amount. It also handles the response type sent to the
	 * client.
	 */
	// The Service is up on localhost and port number 8080
	// Example URL:
	// http://localhost:8080/ExpenseCalculator/trip?destination=mumbai&vehicleType=car&ac=true&fuelType=diesel&numberOfPassengers=6

	@RequestMapping("/trip")
	public ResponseEntity<BigDecimal> calculateTripExpense(
			@RequestParam(value = "destination", required = true) String destination,
			@RequestParam(value = "vehicleType", required = true) String vehicleType,
			@RequestParam(value = "fuelType", defaultValue = "petrol") String fuelType,
			@RequestParam(value = "ac", defaultValue = "false") String ac,
			@RequestParam(value = "numberOfPassengers", required = true) String numberOfPassengers) {
		BigDecimal expenses = null;
		HttpStatus status = null;
		try {
			expenses = expenseCalculatorService.calculateTripExpense(destination, vehicleType, fuelType, ac,
					numberOfPassengers);
			status = HttpStatus.OK;
			return new ResponseEntity<BigDecimal>(expenses, status);
		} catch (ServiceException se) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (ValidationException ve) {
			status = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<BigDecimal>(status);
	}

}

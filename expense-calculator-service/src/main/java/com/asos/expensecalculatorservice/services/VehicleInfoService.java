package com.asos.expensecalculatorservice.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;
import com.asos.expensecalculatorservice.model.Bus;
import com.asos.expensecalculatorservice.model.Car;
import com.asos.expensecalculatorservice.model.IVehicle;
import com.asos.expensecalculatorservice.model.Van;

/**
 * @author rohan
 *
 */
@Service(value = "VehicleInfoService")
public class VehicleInfoService implements IVehicleInfoService {

	@Autowired
	RestTemplate restTemplate;

	/**
	 * This method is a client for Vehicle Info Rest service.
	 */
	@Override
	public IVehicle getVehicleInfo(VehicleType vehicleType, FuelType fuelType, Boolean isAirConditioningRequired) {
		IVehicle vehicle = null;
		URL url = null;
		try {
			url = urlBuilder(vehicleType.toString(), fuelType.toString(), isAirConditioningRequired.toString());
			if (vehicleType == VehicleType.CAR) {
				vehicle = restTemplate.getForObject(url.toString(), Car.class);
			} else if (vehicleType == VehicleType.BUS) {
				vehicle = restTemplate.getForObject(url.toString(), Bus.class);
			} else if (vehicleType == VehicleType.VAN) {
				vehicle = restTemplate.getForObject(url.toString(), Van.class);
			}
		} catch (IOException | RuntimeException e) {
			e.printStackTrace();
		}
		return vehicle;
	}

	/**
	 * @param vehicleType
	 * @param fuelType
	 * @param ac
	 * @return url for fetching Vehicle info from VehicleInfo Service.
	 * @throws MalformedURLException
	 */
	private URL urlBuilder(String vehicleType, String fuelType, String ac) throws MalformedURLException {
		// This is not the best way to store the url. The url is hardcoded for demo
		// purpose.
		final StringBuilder sb = new StringBuilder("http://localhost:8081/vehicles/getVehicle?");
		sb.append("vehicleType=" + vehicleType);
		sb.append("&fuelType=" + fuelType);
		sb.append("&ac=" + ac);
		return new URL(sb.toString());
	}

}

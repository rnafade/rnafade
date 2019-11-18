package com.asos.expensecalculatorservice.services;

import org.springframework.stereotype.Service;

import com.asos.expensecalculatorservice.enums.Locations;

/**
 * @author rohan
 *
 */
@Service(value = "DistanceCalculatorService")
public class DistanceCalculatorService implements IDistanceCalculatorService {
	/**
	 * This method will return distance in KM from source location to destination
	 * location. This can be implemented as a separate microservice which can return
	 * the real time data based on the traffic and the best route.
	 */
	@Override
	public double getDistance(Locations source, Locations destination) {
		double distanceInKM = 0d;
		switch (destination) {
		case PUNE:
			distanceInKM = 0d;
			break;
		case MUMBAI:
			distanceInKM = 200d;
			break;
		case BANGALORE:
			distanceInKM = 1000d;
			break;
		case DELHI:
			distanceInKM = 2050d;
			break;
		case CHENNAI:
			distanceInKM = 1234.5d;
			break;
		default:
			distanceInKM = 0d;
		}
		return distanceInKM;
	}

}

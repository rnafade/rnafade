package com.asos.expensecalculatorservice.services;

import com.asos.expensecalculatorservice.enums.Locations;

public interface IDistanceCalculatorService {
	public double getDistance(Locations source, Locations destination);
}

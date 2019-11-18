package com.asos.expensecalculatorservice.services;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;
import com.asos.expensecalculatorservice.model.IVehicle;

public interface IVehicleInfoService {
	public IVehicle getVehicleInfo(VehicleType vehicleType, FuelType fuelType, Boolean isAirConditioningRequired);
}

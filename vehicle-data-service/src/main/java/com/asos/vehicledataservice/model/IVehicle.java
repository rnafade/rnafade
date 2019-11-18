package com.asos.vehicledataservice.model;

import com.asos.vehicledataservice.enums.FuelType;
import com.asos.vehicledataservice.enums.VehicleType;

public interface IVehicle {

	public VehicleType getVehicleType();

	public Integer getCapacity();

	public FuelType getFuelType();

	public void setFuelType(FuelType fuelType);

	public Boolean isAc();

	public void setAc(Boolean ac);
}

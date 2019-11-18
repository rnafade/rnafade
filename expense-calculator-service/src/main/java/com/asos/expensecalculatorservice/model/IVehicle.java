package com.asos.expensecalculatorservice.model;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;

public interface IVehicle {

	public VehicleType getVehicleType();

	public Integer getCapacity();

	public FuelType getFuelType();

	public void setFuelType(FuelType fuelType);

	public Boolean isAc();

	public void setAc(Boolean ac);
}

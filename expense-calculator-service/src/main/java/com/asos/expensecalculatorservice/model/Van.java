package com.asos.expensecalculatorservice.model;

import org.springframework.stereotype.Component;

import com.asos.expensecalculatorservice.enums.FuelType;
import com.asos.expensecalculatorservice.enums.VehicleType;

@Component(value = "van")
public class Van implements IVehicle {

	private VehicleType vehicleType;

	private Integer capacity;

	private FuelType fuelType;

	private Boolean ac;

	public Van() {

	}

	public Van(VehicleType vehicleType, Integer capacity, FuelType fuelType, Boolean ac) {
		this.vehicleType = vehicleType;
		this.capacity = capacity;
		this.fuelType = fuelType;
		this.ac = ac;
	}

	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	@Override
	public Integer getCapacity() {
		return capacity;
	}

	@Override
	public FuelType getFuelType() {
		return fuelType;
	}

	@Override
	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public Boolean isAc() {
		return ac;
	}

	@Override
	public void setAc(Boolean ac) {
		this.ac = ac;
	}

}

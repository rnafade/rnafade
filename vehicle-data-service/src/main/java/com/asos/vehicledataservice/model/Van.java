package com.asos.vehicledataservice.model;

import org.springframework.stereotype.Component;

import com.asos.vehicledataservice.enums.FuelType;
import com.asos.vehicledataservice.enums.VehicleType;

@Component(value = "van")
public class Van implements IVehicle {

	private VehicleType vehicleType = VehicleType.VAN;

	private Integer capacity = 7;

	private FuelType fuelType = FuelType.PETROL;

	private Boolean ac = Boolean.FALSE;

	public Van() {

	}

	public Van(VanBuilder builder) {
		this.fuelType = builder.fuelType;
		this.ac = builder.ac;
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

	public static class VanBuilder {

		private FuelType fuelType;

		private Boolean ac;

		public VanBuilder fuelType(FuelType fuelType) {
			this.fuelType = fuelType;
			return this;
		}

		public VanBuilder ac(Boolean ac) {
			this.ac = ac;
			return this;
		}

		public IVehicle build() {
			return new Van(this);
		}
	}

}

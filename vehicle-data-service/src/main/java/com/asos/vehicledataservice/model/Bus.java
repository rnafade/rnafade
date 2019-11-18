package com.asos.vehicledataservice.model;

import org.springframework.stereotype.Component;

import com.asos.vehicledataservice.enums.FuelType;
import com.asos.vehicledataservice.enums.VehicleType;

@Component(value = "bus")
public class Bus implements IVehicle {

	private VehicleType vehicleType = VehicleType.BUS;

	private Integer capacity = 50;

	private FuelType fuelType = FuelType.DIESEL;

	private Boolean ac = Boolean.FALSE;

	public Bus() {

	}

	public Bus(BusBuilder builder) {
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

	public static class BusBuilder {

		private Boolean ac;

		public BusBuilder ac(Boolean ac) {
			this.ac = ac;
			return this;
		}

		public IVehicle build() {
			return new Bus(this);
		}
	}

}

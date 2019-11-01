package com.bollock.blockcar.fabric;

import java.util.List;

import com.bollock.blockcar.car.Car;
import com.bollock.blockcar.car.Car;

public interface IFabricSerialRepository {
	public boolean registerCar(String carSerial);
	public boolean updateCarNumber(String carSerial, String carNumber);
	public boolean expireSerial(String carSerial);
	public List<Car> carSerialHistory(String carSerial);
	public Car querySerial(String carSerialNumber);
}

package com.bollock.blockcar.fabric;

import java.util.List;

import com.bollock.blockcar.car.Car;
import com.bollock.blockcar.car.Record;
import com.bollock.blockcar.car.Car;

public interface IFabricRepository {
	public boolean registerAccident(String carSerial, String carData);
	public boolean registerMaintenance(String carSerial, String carData);
	public boolean registerUpdatedOwner(String carSerial, String carData);
	public List<Record> getAccidentHistory(String carSerial);
	public List<Record> getMaintenanceHistory(String carSerial);
	public List<Record> getOwnerHistory(String carSerial);
	public Record queryOwner(String carSerial);
	
}

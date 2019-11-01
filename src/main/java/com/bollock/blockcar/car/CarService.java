package com.bollock.blockcar.car;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bollock.blockcar.fabric.IFabricSerialRepository;
import com.bollock.blockcar.fabric.IFabricRepository;

@Service
public class CarService implements ICarService{
	@Autowired
	private IFabricRepository managementService;
	
	@Autowired
	private IFabricSerialRepository serialService;
	
	@Autowired
	private CarRepository carRepository;
	public String validCheckByCarNumber(String carNumber) {
		Car carByDB = carRepository.findByCarNumber(carNumber);
		if(carByDB == null) return null;
		Car carByFabric = serialService.querySerial(carByDB.getCarSerial());
		if(carByFabric != null && carByFabric.getCarNumber().equals(carNumber)) {
			return carByFabric.getCarSerial();
		}
		else return null;
	}
	
	@Override
	public Car findByCarSerialInFabric(String carSerial) {
		return serialService.querySerial(carSerial);
	}
	
	@Override
	public Car findByCarNumber(String carNumber) {
		return carRepository.findByCarNumber(carNumber);
	}
	/*
	 보완 필요.
	 */
	@Override
	public boolean registerCar(String carSerial, String carNumber, String info) {
		Car car = new Car();
		car.setCarSerial(carSerial);
		car.setCarNumber(carNumber);
		car.setInfo(info);
		carRepository.save(car);
		serialService.registerCar(carSerial);
		serialService.updateCarNumber(carSerial, carNumber);
		return true;
	}

	@Override
	public boolean updateCarNumber(String carSerial, String carNumber) {
		Car car = carRepository.findById(carSerial).get();
		if(car == null) return false;
		try {
			car.setCarNumber(carNumber);
			carRepository.flush();
			serialService.updateCarNumber(carSerial, carNumber);
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean expireSerial(String carSerial) {
		try{
			carRepository.deleteById(carSerial);
			serialService.expireSerial(carSerial);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public Car querySerial(String carSerialNumber) {
		return serialService.querySerial(carSerialNumber);
	}

	@Override
	public boolean registerAccident(String carSerial, String carData) {
		return managementService.registerAccident(carSerial, carData);
	}

	@Override
	public boolean registerMaintenance(String carSerial, String carData) {
		return managementService.registerMaintenance(carSerial, carData);
	}

	@Override
	public boolean registerUpdatedOwner(String carSerial, String carData) {
		return managementService.registerUpdatedOwner(carSerial, carData);
	}

	@Override
	public List<Record> getAccidentHistory(String carSerial) {
		return managementService.getAccidentHistory(carSerial);
	}

	@Override
	public List<Record> getMaintenanceHistory(String carSerial) {
		return managementService.getMaintenanceHistory(carSerial);
	}

	@Override
	public List<Record> getOwnerHistory(String carSerial) {
		return managementService.getOwnerHistory(carSerial);
	}

	@Override
	public Record queryOwner(String carSerial) {
		return managementService.queryOwner(carSerial);
	}

}

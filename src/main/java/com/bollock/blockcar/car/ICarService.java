package com.bollock.blockcar.car;

import java.util.List;

public interface ICarService {
	public Car findByCarNumber(String carNumber);
	public Car findByCarSerialInFabric(String carSerial);
	public boolean registerCar(String carSerial, String carNumber, String option);	// 차량 생성 ( 차량 시리얼 생성 )	
	public boolean updateCarNumber(String carSerial, String carNumber);				// 차량 시리얼 변경
	public boolean expireSerial(String carSerial);									// 차량 시리얼 폐기
//	public List<CarSerial> carSerialHistory(String carSerial);						// 차량 시리얼 이력
	public Car querySerial(String carSerialNumber);									// 최근 차량 시리얼 조회
	
	
	public boolean registerAccident(String carSerial, String carData);			// 사고 기록 등록
	public boolean registerMaintenance(String carSerial, String carData);		// 정비 기록 등록
	public boolean registerUpdatedOwner(String carSerial, String carData);		// 차량 소유권 변경
	public List<Record> getAccidentHistory(String carSerial);						// 사고이력 조회
	public List<Record> getMaintenanceHistory(String carSerial);					// 정비 이력 조회
	public List<Record> getOwnerHistory(String carSerial);							// 소유권 변경 이력 조회
	public Record queryOwner(String carSerial);									// 차량 소유주 조회
		
	public String validCheckByCarNumber(String carNumber);
}

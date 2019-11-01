package com.bollock.blockcar.car;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.milagro.amcl.RSA2048.public_key;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bollock.blockcar.fabric.IFabricRepository;

import io.swagger.annotations.Api;



@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Api(value = "Auth", description = "Auth Control")
public class CarController {
	public static final Logger logger = LoggerFactory.getLogger(CarController.class);

	@Autowired
	private ICarService service;
	
	
	/*
	 * 1. 차량 생성 ( 시리얼 생성 및 차량 넘버/정보 등록 )
	 */
	@RequestMapping(value = "/car", method = RequestMethod.POST)
	public boolean registerCar(@RequestParam String carNumber, @RequestParam String info) {
		if(service.validCheckByCarNumber(carNumber).equals(null)) {
			Random rd = new Random();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
			String carSerial = df.format(date) + rd.nextInt(1000);
			return service.registerCar(carSerial,carNumber,info);
		}else {
			System.out.println("차량 넘버가 중복되었거나, 생성할 수 없습니다.");
			return false;
		}
	}
	
	// 사고 이력 등록
	@PostMapping("/cars/{carNumber}/accident")
	public ResponseEntity<Boolean> addAccident(@PathVariable String carNumber, @RequestParam String accident){
		try {
			Car car = service.findByCarNumber(carNumber);
			service.registerAccident(car.getCarSerial(), accident);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		
	}
	// 정비 이력 등록
	@PostMapping("/cars/{carNumber}/maintenance")
	public ResponseEntity<Boolean> addMaintenance(@PathVariable String carNumber, @RequestParam String maintenance){
		try {
			Car car = service.findByCarNumber(carNumber);
			service.registerMaintenance(car.getCarSerial(), maintenance);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
	// 소유권 이전 등록
	@PostMapping("/cars/{carNumber}/owner")
	public ResponseEntity<Boolean> addOwner(@PathVariable String carNumber, @RequestParam String owner){
		try {
			Car car = service.findByCarNumber(carNumber);
			service.registerUpdatedOwner(car.getCarSerial(), owner);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch(Exception e) {
			System.out.println(e);
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
	}
	
	// 전체 이력 조회
	// ( 사고 이력 조회 )
	// ( 정비 이력 조회 )
	// ( 소유권 이전 조회 )
	
	@GetMapping("/cars/{carNumber}/history")
	public ResponseEntity<Car> getCarHistory(@PathVariable String carNumber){
		
		String serial = service.validCheckByCarNumber(carNumber);
		if(serial.equals(null)) return null;
		Car car = service.findByCarNumber(carNumber);
		car.setAccident(service.getAccidentHistory(serial));
		car.setMaintenance(service.getMaintenanceHistory(serial));
		car.setOwner(service.getOwnerHistory(serial));
		
		return new ResponseEntity<Car>(car,HttpStatus.OK);
	}
	
	// 차량 소유주 조회
}


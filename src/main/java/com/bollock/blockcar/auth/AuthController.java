package com.bollock.blockcar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bollock.blockcar.car.Car;
import com.bollock.blockcar.car.ICarService;
import com.bollock.blockcar.car.Record;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Api(value = "Auth", description = "Auth Control")
public class AuthController {
	
	@Autowired
	private NaverService naverService;
	public void setNaverService(NaverService naverService) {
		this.naverService = naverService;
	}
	
	@Autowired
	private ICarService service;
	
	@ApiOperation(value = "사용자 인증")
	@PostMapping("/auth/user")
	public ResponseEntity<Record> authUser(@RequestBody Auth auth){
		String carSerial = service.validCheckByCarNumber(auth.getCar_no());
		Record car = service.queryOwner(carSerial);
		return new ResponseEntity<Record>(car ,HttpStatus.OK);
	}

	@PostMapping("/auth/phone")
	public ResponseEntity<Boolean> authPhone(@RequestBody  SmsRequest smsRequest){
		boolean result = naverService.requestSms(smsRequest);
		return new ResponseEntity<Boolean>(result,HttpStatus.OK);
	}
}

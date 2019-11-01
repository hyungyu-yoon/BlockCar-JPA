package com.bollock.blockcar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Api(value = "Auth", description = "Auth Control")
public class AuthController {
	
//	@Autowired
//	private NaverService naverService;
//	public void setNaverService(NaverService naverService) {
//		this.naverService = naverService;
//	}
//	
//	@Autowired
//	private IFabricService fabricService;
//	public void setFabricService(IFabricService fabricService) {
//		this.fabricService = fabricService;
//	}
//	
//	@ApiOperation(value = "사용자 인증")
//	@PostMapping("/auth/user")
//	public ResponseEntity<Car> authUser(@RequestBody Auth auth){
////		Car car = fabricService.query(auth.getCar_no());
////		return new ResponseEntity<Car>(car,HttpStatus.OK);
//		return null;
//	}
//
//	@PostMapping("/auth/phone")
//	public ResponseEntity<Boolean> authPhone(@RequestBody  SmsRequest smsRequest){
//		boolean result = naverService.requestSms(smsRequest);
//		return new ResponseEntity<Boolean>(result,HttpStatus.OK);
//	}
}

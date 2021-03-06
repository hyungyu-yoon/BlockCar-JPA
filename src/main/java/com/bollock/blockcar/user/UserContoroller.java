package com.bollock.blockcar.user;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.bollock.blockcar.car.ICarService;
import com.bollock.blockcar.purchase.IPurchaseService;
import com.bollock.blockcar.purchase.Purchase;
import com.bollock.blockcar.sales.ISalesService;
import com.bollock.blockcar.sales.Sales;
import com.bollock.blockcar.util.SecurityUtil;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserContoroller {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISalesService salesService;
	
	@Autowired
	private IPurchaseService purchaseService;
	
	@Autowired
	private ICarService carService;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

	@PostMapping("/users")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		user.setPassword(new SecurityUtil().encryptSHA256(user.getPassword()));
		user.setType("user");
		User temp = userService.insertUser(user);

//		if (user.getId().matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
//
//
//		} else {
//
//
//		}

		if (temp == null) {
			return new ResponseEntity<User>(temp, HttpStatus.OK);
		}

		return new ResponseEntity<User>(temp, HttpStatus.OK);
	}

	@GetMapping("/users")
	public ResponseEntity<List<User>> getUserAll() {
		List<User> users = userService.findUserAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@GetMapping("/users/{no}")
	public ResponseEntity<User> getUser(@PathVariable Long no) {
		User user = userService.findUser(no);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@PutMapping("/users")
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User temp = userService.findUser(user.getNo());

		temp = userService.updateUser(temp);

		return new ResponseEntity<User>(temp, HttpStatus.OK);
	}

	@PostMapping("/users/auth")
	public ResponseEntity<User> authUser(@RequestBody User user) {
		user.setPassword(new SecurityUtil().encryptSHA256(user.getPassword()));
		User temp = userService.findUserById(user.getId());

		if (!user.getPassword().equals(temp.getPassword())) {
			temp = null;
		}

		return new ResponseEntity<User>(temp, HttpStatus.OK);
	}
	
	@GetMapping("/users/{no}/sales")
	public ResponseEntity<List<Sales>> getUserSales(@PathVariable Long no){
		List<Sales> list = salesService.findUserSales(no);
		
		return new ResponseEntity<List<Sales>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/users/{no}/purchase")
	public ResponseEntity<List<Purchase>> getUserPurchase(@PathVariable Long no){
		List<Purchase> list = purchaseService.findPurchasesAll();
		List<Purchase> list2 = new ArrayList<Purchase>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getUser().getNo() == no){
				list.get(i).getPost().setCar(carService.findByCarNumber(list.get(i).getPost().getCarno()));
				list2.add(list.get(i));
			}
		}
		
		return new ResponseEntity<List<Purchase>>(list2,HttpStatus.OK);
	}
}

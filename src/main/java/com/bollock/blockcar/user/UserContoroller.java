package com.bollock.blockcar.user;

import java.util.List;

import org.apache.milagro.amcl.RSA2048.public_key;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bollock.blockcar.post.IPostService;
import com.bollock.blockcar.post.Post;
import com.bollock.blockcar.sales.ISalesService;
import com.bollock.blockcar.sales.Sales;
import com.bollock.blockcar.util.SecurityUtil;

@RestController
@RequestMapping("/api")
public class UserContoroller {
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ISalesService salesService;
	
	@Autowired
	private IPostService postService;
	
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
	
	@DeleteMapping("/users")
	public ResponseEntity<Boolean> deleteUser(@RequestBody User user){
		try {
			userService.deleteUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

	@PostMapping("/users/auth")
	public ResponseEntity<Boolean> authUser(@RequestBody User user) {
		user.setPassword(new SecurityUtil().encryptSHA256(user.getPassword()));
		User temp = userService.findUser(user.getNo());

		boolean result = true;
		if (!user.getPassword().equals(temp.getPassword())) {
			result = false;
		}

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping("/users/{no}/sales")
	public ResponseEntity<List<Sales>> getUserSales(@PathVariable Long no){
		List<Sales> list = salesService.findUserSales(no);
		
		return new ResponseEntity<List<Sales>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/users/{no}/posts")
	public ResponseEntity<List<Post>> getUserPosts(@PathVariable Long no){
		List<Post> list = postService.findUserPosts(no);
		return new ResponseEntity<List<Post>>(list,HttpStatus.OK);
	}
}

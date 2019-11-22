package com.bollock.blockcar.post;

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

import com.bollock.blockcar.car.Car;
import com.bollock.blockcar.car.ICarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Api(value = "Post", description = "Post Controller")
public class PostController {
	@Autowired
	private IPostService postService;
	
	@Autowired
	private ICarService service;
	
	@ApiOperation(value = "판매 게시물 등록")
	@PostMapping("/posts")
	public ResponseEntity<Post> addPost(@RequestBody Post post){
		post.setState("판매중");
		Post postInfo = postService.insertPost(post);
		
		return new ResponseEntity<Post>(postInfo,HttpStatus.OK);
	}
	
	@ApiOperation(value = "전체 게시물")
	@GetMapping("/posts")
	public ResponseEntity<List<Post>> getPostsAll(){
		List<Post> list = postService.findPostsAll();
		for (int i = 0; i < list.size(); i++) {
			Car car = service.findByCarNumber(list.get(i).getCarno());
			list.get(i).setCar(car);
		}
		return new ResponseEntity<List<Post>>(list,HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시물 정보")
	@GetMapping("/posts/{no}")
	public ResponseEntity<Post> getPost(@PathVariable Long no){
		Post post = postService.findPost(no);
		String serial = service.validCheckByCarNumber(post.getCarno());
		if(serial.equals(null)) return null;
		Car car = service.findByCarNumber(post.getCarno());
		car.setAccident(service.getAccidentHistory(serial));
		car.setMaintenance(service.getMaintenanceHistory(serial));
		car.setOwner(service.getOwnerHistory(serial));
		
		post.setCar(car);
		
		return new ResponseEntity<Post>(post,HttpStatus.OK);
	}
	
	@ApiOperation(value = "판매 상태 업데이트")
	@PutMapping("/posts")
	public ResponseEntity<Post> updateState(@RequestBody Post post){
		Post postInfo = postService.findPost(post.getNo());
		postInfo.setState(post.getState());

		postInfo = postService.updatePost(postInfo);
		return new ResponseEntity<Post>(postInfo, HttpStatus.OK);
	}
}

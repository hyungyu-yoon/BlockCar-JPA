package com.bollock.blockcar.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
@Api(value = "Purchase", description = "Purchase Control")
public class PurchaseController {
	@Autowired
	private IPurchaseService purchaseService;
	
	@PostMapping("/purchases")
	public ResponseEntity<Purchase> addPurchase(@RequestBody Purchase purchase){
		purchase.setState("구매완료");
		Purchase info = purchaseService.insertPurchase(purchase);
		return new ResponseEntity<Purchase>(info,HttpStatus.OK);
	}
	
	@GetMapping("/purchases")
	public ResponseEntity<List<Purchase>> getPurchasesAll(){
		List<Purchase> list = purchaseService.findPurchasesAll();
		return new ResponseEntity<List<Purchase>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/purchases/{no}")
	public ResponseEntity<Purchase> getPurchase(@PathVariable Long no){
		Purchase purchase = purchaseService.findPurchase(no);
		return new ResponseEntity<Purchase>(purchase,HttpStatus.OK);
	}
	
	@PutMapping("/purchases")
	public ResponseEntity<Purchase> updatePurchase(@RequestBody Purchase purchase){
		Purchase info = purchaseService.findPurchase(purchase.getNo());
		info.setState(purchase.getState());
		purchaseService.updatePurchase();
		
		return new ResponseEntity<Purchase>(info,HttpStatus.OK);
	}
	
	@DeleteMapping("/purchases")
	public ResponseEntity<Boolean> deletePurchase(@RequestBody Purchase purchase){
		boolean check = true;
		try {
			purchaseService.deletePurchase(purchase);
		} catch (Exception e) {
			e.printStackTrace();
			check = false;
		}
		return new ResponseEntity<Boolean>(check,HttpStatus.OK);
	}
	
	
}

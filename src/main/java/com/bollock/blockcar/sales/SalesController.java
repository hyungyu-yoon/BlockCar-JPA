package com.bollock.blockcar.sales;

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

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class SalesController {
	@Autowired
	private ISalesService salesService;
	
	@PostMapping("/sales")
	public ResponseEntity<Sales> addSales(@RequestBody Sales sales){
		sales.setState("신청완료");
		Sales temp = salesService.insertSales(sales);
		return new ResponseEntity<Sales>(temp,HttpStatus.OK);
	}
	
	@GetMapping("/sales")
	public ResponseEntity<List<Sales>> getSalesAll(){
		List<Sales> list = salesService.findSalesAll();
		return new ResponseEntity<List<Sales>>(list,HttpStatus.OK);
	}
	
	@GetMapping("/sales/{no}")
	public ResponseEntity<Sales> getSales(@PathVariable Long no){
		Sales sales = salesService.findSales(no);
		return new ResponseEntity<Sales>(sales,HttpStatus.OK);
	}
	
	@PutMapping("/sales")
	public ResponseEntity<Boolean> updateSales(@RequestBody Sales sales){
		Sales temp = salesService.findSales(sales.getNo());
		temp.setState(sales.getState());
		try {
			salesService.updateSales();
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false,HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}

}
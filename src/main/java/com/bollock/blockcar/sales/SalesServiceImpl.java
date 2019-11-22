package com.bollock.blockcar.sales;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bollock.blockcar.user.User;

@Service
public class SalesServiceImpl implements ISalesService {
	@Autowired
	private SalesRepository salesRepository;
	
	@Override
	public Sales insertSales(Sales sales) {
		return salesRepository.save(sales);
	}

	@Override
	public List<Sales> findSalesAll() {
		return salesRepository.findAll();
	}

	@Override
	public Sales findSales(Long no) {
		return salesRepository.findById(no).get();
	}

	@Override
	public List<Sales> findUserSales(Long no) {
		return salesRepository.findByUserNo(no);
	}

	@Override
	public void updateSales() {
		salesRepository.flush();
	}

}

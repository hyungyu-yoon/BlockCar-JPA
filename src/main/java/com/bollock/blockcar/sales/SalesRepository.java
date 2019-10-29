package com.bollock.blockcar.sales;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesRepository extends JpaRepository<Sales, Long> {
	public List<Sales> findByUserNo(Long no);
}

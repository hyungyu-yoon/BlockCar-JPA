package com.bollock.blockcar.sales;

import java.util.List;


public interface ISalesService {

	Sales insertSales(Sales sales);
	List<Sales> findSalesAll();
	Sales findSales(Long no);
	List<Sales> findUserSales(Long no);
}

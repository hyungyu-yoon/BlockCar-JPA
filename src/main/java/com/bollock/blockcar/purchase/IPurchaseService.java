package com.bollock.blockcar.purchase;

import java.util.List;

public interface IPurchaseService {

	Purchase insertPurchase(Purchase purchase);

	List<Purchase> findPurchasesAll();

	void deletePurchase(Purchase purchase);

	Purchase findPurchase(Long no);
	void updatePurchase();
	List<Purchase> findUserPurchases(Long no);
}

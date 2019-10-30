package com.bollock.blockcar.purchase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class PurchaseServiceImpl implements IPurchaseService {
	@Autowired
	private PurchaseRepository purchaseRepository;

	@Override
	public Purchase insertPurchase(Purchase purchase) {
		return purchaseRepository.save(purchase);
	}

	@Override
	public List<Purchase> findPurchasesAll() {
		return purchaseRepository.findAll();
	}

	@Override
	public void deletePurchase(Purchase purchase) {
		purchaseRepository.deleteById(purchase.getNo());
	}

	@Override
	public Purchase findPurchase(Long no) {
		return purchaseRepository.findById(no).get();
	}

	@Override
	public void updatePurchase() {
		purchaseRepository.flush();
	}

}

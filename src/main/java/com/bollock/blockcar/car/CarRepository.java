package com.bollock.blockcar.car;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, String>{
	Car findByCarNumber(String carNumber);
	
}

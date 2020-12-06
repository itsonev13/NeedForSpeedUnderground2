package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
	Optional<Car> findByBrand(String brand);
}

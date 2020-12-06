package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Integer> {
	Optional<District> findByName(String name);
}

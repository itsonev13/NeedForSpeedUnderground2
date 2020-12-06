package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Racer;

@Repository
public interface RacerRepository extends JpaRepository<Racer, Integer> {
	Optional<Racer> findByName(String name);
}

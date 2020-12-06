package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.entities.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
	Optional<Town> findByName(String name);

	@Query("FROM Town t JOIN t.racers r GROUP BY t")
	List<Town> exportTowns();
}

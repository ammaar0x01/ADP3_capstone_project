package com.college.repository;

import com.college.domain.employees.FoodWorker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodWorkerRepository extends JpaRepository<FoodWorker, Integer> {
}

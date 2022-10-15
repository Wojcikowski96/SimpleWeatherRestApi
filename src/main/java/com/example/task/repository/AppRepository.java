package com.example.task.repository;

import com.example.task.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface AppRepository extends JpaRepository<City, Long> {
}

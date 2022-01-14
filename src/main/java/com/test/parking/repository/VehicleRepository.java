package com.test.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.parking.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long>{

}

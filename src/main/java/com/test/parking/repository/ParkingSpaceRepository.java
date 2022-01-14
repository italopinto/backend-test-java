package com.test.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.parking.model.ParkingSpace;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

}

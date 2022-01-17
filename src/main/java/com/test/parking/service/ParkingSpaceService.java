package com.test.parking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.test.parking.model.Company;
import com.test.parking.model.ParkingSpace;
import com.test.parking.model.SpaceStatus;
import com.test.parking.repository.ParkingSpaceRepository;

@Service
public class ParkingSpaceService {
	
	@Autowired
	private ParkingSpaceRepository parkingRepository;
	
	public void companySpaces(Company company, int motorcycles, int cars) {
		ParkingSpace newSpace = new ParkingSpace();
		
		if (motorcycles != 0) {
			for (int i = 1; i <= motorcycles; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("motorcycle");
				newSpace.setCompany(company);
				parkingRepository.save(newSpace);
			}
		}
		
		if (cars != 0) {
			for (int i = 1; i <= cars; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("car");
				newSpace.setCompany(company);
				parkingRepository.save(newSpace);
			}
		}
	}
	
	public void companySpacesUpdate(Company company, int motorcyclesDifferenceSpaces, int carsDifferenceSpaces) {
		ParkingSpace newSpace = new ParkingSpace();
		
		if (motorcyclesDifferenceSpaces < 0) {
			for (int i = 1; i <= motorcyclesDifferenceSpaces*-1; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("motorcycle");
				newSpace.setCompany(company);
				parkingRepository.save(newSpace);
			}
		} else if (motorcyclesDifferenceSpaces > 0) {
			List<ParkingSpace> spacesToDelete = parkingRepository.findAll(Sort.by("motorcycle").and(Sort.by("space_number").descending()));
			for (int i = 1; i <= motorcyclesDifferenceSpaces; i++) {
				parkingRepository.delete(spacesToDelete.get(i));
			}
		}

		if (carsDifferenceSpaces < 0) {
			for (int i = 1; i <= carsDifferenceSpaces*-1; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("car");
				newSpace.setCompany(company);
				parkingRepository.save(newSpace);
			}
		} else if (carsDifferenceSpaces > 0) {
			List<ParkingSpace> spacesToDelete = parkingRepository.findAll(Sort.by("car").and(Sort.by("space_number").descending()));
			for (int i = 1; i <= carsDifferenceSpaces; i++) {
				parkingRepository.delete(spacesToDelete.get(i));
			}
		}
		
		
	}
	
	public ParkingSpace availableSpace() {
		ParkingSpace parkingSpace = new ParkingSpace();
		parkingSpace.setSpaceStatus(SpaceStatus.FREE);
		Example<ParkingSpace> parkingExample = Example.of(parkingSpace);
		return parkingRepository.findOne(parkingExample).orElse(null);
	}
	
	/*
	 * Method to change status of a parking space
	 * from FREE to OCCUPIED.
	 * @param space The ParkingSpace entity to set the status
	 * @param status the status you want to set. 1 for FREE or 2 for OCCUPIED
	 * 
	 * */
	public void changeStatus(ParkingSpace space, int status) {
		switch (status) {
		case 1: 
			space.setSpaceStatus(SpaceStatus.FREE);
			parkingRepository.save(space);
			break;
		case 2:
			space.setSpaceStatus(SpaceStatus.OCCUPIED);
			parkingRepository.save(space);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + status);
		}
		
	}
}

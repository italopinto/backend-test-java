package com.test.parking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.test.parking.model.Company;
import com.test.parking.model.ParkingSpace;
import com.test.parking.repository.CompanyRepository;
import com.test.parking.repository.ParkingSpaceRepository;

@Service
public class CompanyService {
	/*
	 * Criar as vagas de estacionamento na tabela de estacionamento
	 * no momento da criação da company ✔
	 * 
	 * como vai ser quando atualizar o numero de vagas de estacionamento por tipo? ✔
	 * 
	 * O controller não deve deixar vir valores negativos de número de vagas de estacionamento
	 * */
	@Autowired
	private CompanyRepository companyRepository;
	
	@Autowired
	private ParkingSpaceRepository parkingSpace;
	
	public Company addCompany(Company company) {
		int motorcycle = company.getMotorcyclesSpace();
		int car = company.getCarsSpace();
		ParkingSpace newSpace = new ParkingSpace();
		if (motorcycle != 0) {
			for (int i = 1; i <= motorcycle; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("motorcycle");
				parkingSpace.save(newSpace);
			}
		}
		if (car != 0) {
			for (int i = 1; i <= car; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("car");
				parkingSpace.save(newSpace);
			}
		}
		return companyRepository.save(company);
	}
	
	public List<Company> listCompany() {
		return companyRepository.findAll();
	}
	
	public Company getCompanyById(long id) {
		return companyRepository.findById(id).orElse(null);
	}
	
	public String deleteCompany(long id) {
		companyRepository.deleteById(id);
		return "Company id: " + id + ", has been deleted successfuly.";
	}
	
	public Company updateCompany(Company company) {
		Company existingCompany = companyRepository.findById(company.getId()).orElse(null);
		existingCompany.setName(company.getName());
		existingCompany.setCnpj(company.getCnpj());
		existingCompany.setAddress(company.getAddress());
		existingCompany.setPhone(company.getPhone());
		existingCompany.setMotorcyclesSpace(company.getMotorcyclesSpace());
		existingCompany.setCarsSpace(company.getCarsSpace());
		
		ParkingSpace newSpace = new ParkingSpace();
		
		int carsDifferenceSpaces = existingCompany.getCarsSpace() - company.getCarsSpace();
		if (carsDifferenceSpaces < 0) {
			for (int i = 1; i <= carsDifferenceSpaces*-1; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("car");
				parkingSpace.save(newSpace);
			}
		} else if (carsDifferenceSpaces > 0) {
			List<ParkingSpace> spacesToDelete = parkingSpace.findAll(Sort.by("car").and(Sort.by("space_number").descending()));
			parkingSpace.deleteAll(spacesToDelete);
		}
		
		int motorcyclesDifferenceSpaces = existingCompany.getMotorcyclesSpace() - company.getMotorcyclesSpace();
		if (motorcyclesDifferenceSpaces < 0) {
			for (int i = 1; i <= motorcyclesDifferenceSpaces*-1; i++) {
				newSpace.setSpaceNumber(i);
				newSpace.setSpaceType("motorcycle");
				parkingSpace.save(newSpace);
			}
		} else if (motorcyclesDifferenceSpaces > 0) {
			List<ParkingSpace> spacesToDelete = parkingSpace.findAll(Sort.by("motorcycle").and(Sort.by("space_number").descending()));
			parkingSpace.deleteAll(spacesToDelete);
		}
		
		return companyRepository.save(existingCompany);
	}
}

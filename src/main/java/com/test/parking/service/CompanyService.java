package com.test.parking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.parking.model.Company;
import com.test.parking.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company addCompany(Company company) {
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
		return companyRepository.save(existingCompany);
	}
}

package com.test.parking.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.test.parking.model.Company;
import com.test.parking.service.CompanyService;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/parking")
public class ParkingController {
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping(value = "/company")
	@ResponseStatus(HttpStatus.CREATED)
	public Company registerCompany(@Valid @RequestBody Company company) {
		return companyService.addCompany(company);
	}
	
	@GetMapping(value = "/company")
	public List<Company> listCompany() {
		return companyService.listCompany();
	}
	
	@GetMapping(value = "/company/{id}")
	public Company listOneCompany(@PathVariable long id) {
		return companyService.getCompanyById(id);
	}
	
	@PostMapping(value = "/company/{id}")
	public Company updateCompany(@Valid @RequestBody Company company, @PathVariable long id) {
		return companyService.updateCompany(company, id);
	}
	
	@DeleteMapping(value = "/company/{id}")
	public String deleteCompany(@PathVariable long id) {
		companyService.deleteCompany(id);
		return "Company id: " + id + ", has been successfuly deleted";
	}
	
}

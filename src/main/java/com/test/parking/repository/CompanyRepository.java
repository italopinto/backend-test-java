package com.test.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.parking.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}

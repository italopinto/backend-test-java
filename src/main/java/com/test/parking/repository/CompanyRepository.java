package com.test.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.parking.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

}

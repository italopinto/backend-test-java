package com.test.parking.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private int cnpj;
	
	@Column(nullable = false)
	private String address;
	
	@Column(nullable = false)
	private int phone;
	
	@Column(nullable = false)
	private int motorcyclesSpace;
	
	@Column(nullable = false)
	private int carsSpace;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ParkingSpace> parkingSpaces; 
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
	
}

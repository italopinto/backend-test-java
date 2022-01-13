package com.test.parking.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "space_id")
	@JsonBackReference
	private ParkingSpace space;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "vehicle_id")
	@JsonBackReference
	private Vehicle vehicle;
	
	private Timestamp entryTime;
	
	private Timestamp exitTime;
}

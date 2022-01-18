package com.test.parking.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ticket")
public class Ticket extends Auditable implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "space_id")
	@JsonBackReference(value = "parkingSpace")
	private ParkingSpace parkingSpace;
	
	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	@JsonBackReference(value = "vehicle")
	private Vehicle vehicle;
	
	private Timestamp exitTime;
	
}

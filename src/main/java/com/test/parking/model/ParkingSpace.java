package com.test.parking.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Data
@Entity
@Table(name = "parking_space")
public class ParkingSpace {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private int spaceNumber;
	
	@Column(nullable = false)
	private String spaceType;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private SpaceStatus spaceStatus;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "company_id")
	@JsonBackReference
	private Company company;
	
	@JsonManagedReference
    @OneToMany(mappedBy = "ticket", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ticket> tickets;
	
	private Timestamp createdAt;
	
	private Timestamp updatedAt;
}

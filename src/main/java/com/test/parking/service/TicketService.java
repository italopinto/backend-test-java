package com.test.parking.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.test.parking.model.ParkingSpace;
import com.test.parking.model.Ticket;
import com.test.parking.repository.TicketRepository;

public class TicketService {
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private ParkingSpaceService parkingService;
	
	public Ticket entranceTicket(Ticket ticket) {
		ParkingSpace availableSpace = parkingService.availableSpace();
		ticket.setSpace(availableSpace);
		parkingService.changeStatus(availableSpace, 2);
		return ticketRepository.save(ticket);
	}
	
	public Ticket exitTicket(Ticket ticket) {
		parkingService.changeStatus(ticket.getSpace(), 1);
		ticket.setExitTime(Timestamp.from(Instant.now()));
		return ticketRepository.save(ticket);
	}
	
	public List<Ticket> listTicket() {
		return ticketRepository.findAll();
	}
}

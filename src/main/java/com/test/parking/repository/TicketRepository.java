package com.test.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.parking.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}

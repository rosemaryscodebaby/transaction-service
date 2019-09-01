package com.bingobucket.ticket.repository;


import com.bingobucket.ticket.entity.Ticket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, String> {

}

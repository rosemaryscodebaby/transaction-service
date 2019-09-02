package com.ipsx.transaction.repository;


import com.ipsx.transaction.entity.Ticket;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface TicketRepository extends ReactiveCrudRepository<Ticket, String> {

}

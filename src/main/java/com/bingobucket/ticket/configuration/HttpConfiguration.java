package com.bingobucket.ticket.configuration;


import com.bingobucket.ticket.entity.Ticket;
import com.bingobucket.ticket.repository.TicketRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class HttpConfiguration {

    @Bean
    RouterFunction<ServerResponse> routeTickets(TicketRepository ticketRepository) {
        return route()
                .GET("/tickets", serverRequest -> ok().body(ticketRepository.findAll(), Ticket.class))
                .build();
    }

}

package com.ipsx.transaction.initializr;

import com.ipsx.transaction.entity.Ticket;
import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.repository.TicketRepository;
import com.ipsx.transaction.service.impl.DisplayServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.ipsx.transaction.configuration.Constants.COLUMN_LENGTH_OF_EACH_TICKET;
import static com.ipsx.transaction.configuration.Constants.TOTAL_NUMBER_OF_TICKETS;

/**
 * Persist ticket data on htttp request e.g. http://localhost:8080/tickets
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class TicketDataInitializr {

    private final TicketRepository ticketRepository;

    private TicketGenerator ticketGenerator = new TicketGenerator();

    private DisplayServiceImpl displayService = new DisplayServiceImpl();


    @EventListener(ApplicationReadyEvent.class)
    public void perist() {

        String ticketGroupId = UUID.randomUUID().toString();

        Transaction transaction = ticketGenerator.generateValidTicket().get();

        //Break down into individual tickets
        String[] ticketArr = new String[TOTAL_NUMBER_OF_TICKETS];
        for (int i = 0; i < TOTAL_NUMBER_OF_TICKETS; i++) {
            ticketArr[i] = IntStream.range(i * COLUMN_LENGTH_OF_EACH_TICKET, (i + 1) * COLUMN_LENGTH_OF_EACH_TICKET).boxed()
                    .map(indx -> displayService.doPrint(transaction.getData(), indx).replace('\t', ' '))
                    .collect(Collectors.joining("\n"));
        }

        var tickets = Flux.just(
                ticketArr
        )
                .map(value -> new Ticket(null, ticketGroupId, value))
                .flatMap(this.ticketRepository::save);


        this.ticketRepository
                .deleteAll()
                .thenMany(tickets)
                .thenMany(this.ticketRepository.findAll())
                .subscribe(log::info);
    }




}

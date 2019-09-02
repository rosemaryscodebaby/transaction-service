package com.ipsx.transaction.initializr;

import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.service.ValidationService;
import com.ipsx.transaction.service.impl.DisplayServiceImpl;
import com.ipsx.transaction.service.impl.GenerationServiceImpl;
import com.ipsx.transaction.service.impl.ValidationServiceImpl;

import java.util.Optional;

public class TicketGenerator {

    Optional<Transaction> generateValidTicket() {
        int attemptCount = 0;
        boolean isValid;

        TicketFacade controller = new TicketFacade(new GenerationServiceImpl(), new DisplayServiceImpl());

        ValidationService ticketValidatorService = new ValidationServiceImpl();


        // Generate, validate and retry in the event that a random ticket could not be reconciled
        Optional<Transaction> transaction;
        do {
            attemptCount++;
            transaction = controller.generateMyTickets();
            isValid = ticketValidatorService.validate(transaction.get());
        } while(!isValid);

        return transaction;
    }
}

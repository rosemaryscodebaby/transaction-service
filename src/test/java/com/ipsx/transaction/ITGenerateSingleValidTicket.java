package com.ipsx.transaction;

import com.ipsx.transaction.initializr.TicketFacade;
import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.service.ValidationService;
import com.ipsx.transaction.service.impl.DisplayServiceImpl;
import com.ipsx.transaction.service.impl.GenerationServiceImpl;
import com.ipsx.transaction.service.impl.ValidationServiceImpl;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.IntStream;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class ITGenerateSingleValidTicket {


    Optional<Transaction> transaction;

    @Test
    public void testE2E() {
        System.out.println("======================== Welcome to Bingo90 ========================");

        int attemptCount = 0;
        boolean isValid;

        TicketFacade controller = new TicketFacade(new GenerationServiceImpl(), new DisplayServiceImpl());

        ValidationService ticketValidatorService = new ValidationServiceImpl();


        // Generate, validate and retry in the event that a random ticket could not be reconciled
        do {
            attemptCount++;
            transaction = controller.generateMyTickets();
            isValid = ticketValidatorService.validate(transaction.get());
        } while(!isValid);

        System.out.println("Printing tickets on attempt#" + attemptCount);

        System.out.println("====================== Your Pack of 6 Tickets ======================");

        assertTrue(transaction.isPresent());
        controller.printMyTickets(transaction.get());

        System.out.println("========================== End of Tickets ==========================");

        IntStream.range(0, 9).forEach(indx -> assertColumnBlankCount(transaction, indx));

    }

    private void assertColumnBlankCount(Optional<Transaction> transaction, int indx) {
        final long expectColumnBlankCount = 8L;
        long actualColumnBlankCount = transaction.get().getData().get(indx).stream().filter(integer -> integer == -1).count();
        assertEquals(expectColumnBlankCount, actualColumnBlankCount);
    }


}

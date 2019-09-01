package com.bingobucket.ticket.runner;

import com.bingobucket.ticket.controller.MyTicketController;
import com.bingobucket.ticket.model.BingoStrip;
import com.bingobucket.ticket.service.ValidationService;
import com.bingobucket.ticket.service.impl.DisplayServiceImpl;
import com.bingobucket.ticket.service.impl.GenerationServiceImpl;
import com.bingobucket.ticket.service.impl.ValidationServiceImpl;

public class TicketGenerationRunner {

    public static void main(String... args) {
        System.out.println("======================== Welcome to Bingo90 ========================");

        int attemptCount = 0;
        boolean isValid;

        MyTicketController controller = new MyTicketController(new GenerationServiceImpl(), new DisplayServiceImpl());

        ValidationService ticketValidatorService = new ValidationServiceImpl();

        BingoStrip bingoStrip;

        // Generate, validate and retry in the event that a random ticket could not be reconciled
        do {
            attemptCount++;
            bingoStrip = controller.generateMyTickets();
            isValid = ticketValidatorService.validate(bingoStrip);
        } while(!isValid);

        System.out.println("Printing tickets on attempt#" + attemptCount);// DBG

        System.out.println("====================== Your Pack of 6 Tickets ======================");

        controller.printMyTickets(bingoStrip);

        System.out.println("========================== End of Tickets ==========================");
    }


}

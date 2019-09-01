package com.bingobucket.ticket.controller;

import com.bingobucket.ticket.model.Transaction;
import com.bingobucket.ticket.service.DisplayService;
import com.bingobucket.ticket.service.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.bingobucket.ticket.configuration.Constants.ROW_LENGTH_OF_EACH_TICKET;

public class MyTicketController {

    //@Autowired
    private GenerationService generationService;

    //@Autowired
    private DisplayService displayService;

    public MyTicketController(GenerationService generationService, DisplayService ticketDisplayService) {
        this.generationService = generationService;
        this.displayService = ticketDisplayService;
    }

    public Transaction generateMyTickets() {
        System.out.println(">> generateMyTickets");
        //Step A.0 initialize to all 0s
        Transaction transaction = new Transaction();

        //Step A.1 assign empty spaces
        transaction = generationService.translateColumn(generationService.assignEmptySpaceToRow(transaction));

        //Step A.2 assign numbers
        for(int c = 0; c < ROW_LENGTH_OF_EACH_TICKET; c++) {
            List<Integer> val = generationService.assignNumberToColumn(transaction.getData().get(c), c);
            transaction.setColumn(c, val);
        }
        System.out.println("<< generateMyTickets");
        return transaction;
    }

    public Optional<Transaction> generateFromEmptyTransaction(Transaction transaction) {
        System.out.println(">> generateFromEmptyTransaction");

        //Step A.1 assign empty spaces
        transaction = generationService.translateColumn(generationService.assignEmptySpaceToRow(transaction));

        //Step A.2 assign numbers
        for(int c = 0; c < ROW_LENGTH_OF_EACH_TICKET; c++) {
            List<Integer> val = generationService.assignNumberToColumn(transaction.getData().get(c), c);
            transaction.setColumn(c, val);
        }
        System.out.println("<< generateFromEmptyTransaction");
        return Optional.of(transaction);
    }

    public void printMyTickets(Transaction transaction) {
        displayService.print(transaction);
    }

}

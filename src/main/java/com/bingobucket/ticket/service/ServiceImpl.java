package com.bingobucket.ticket.service;

import com.bingobucket.ticket.controller.MyTicketController;
import com.bingobucket.ticket.model.Transaction;
import com.bingobucket.ticket.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/*
        System.out.println("======================== Welcome to Bingo90 ========================");

        int attemptCount = 0;
        boolean isValid;

        MyTicketController controller = new MyTicketController(new GenerationServiceImpl(), new DisplayServiceImpl());

        ValidationService ticketValidatorService = new ValidationServiceImpl();

        Transaction transaction;

        // Generate, validate and retry in the event that a random ticket could not be reconciled
        do {
            attemptCount++;
            transaction = controller.generateMyTickets();
            isValid = ticketValidatorService.validate(transaction);
        } while(!isValid);

        System.out.println("Printing tickets on attempt#" + attemptCount);// DBG

        System.out.println("====================== Your Pack of 6 Tickets ======================");

        controller.printMyTickets(transaction);

        System.out.println("========================== End of Tickets ==========================");
 */
@org.springframework.stereotype.Service
@AllArgsConstructor
public class ServiceImpl implements Service {

    private GenerationService generationService;

    private DisplayService displayService;

    private ValidationService validatorService;

    private MyTicketController controller;

    @Autowired
    public ServiceImpl(GenerationService generationService, DisplayService displayService, ValidationService validationService) {
        this.generationService = generationService;
        this.displayService = displayService;
        this.validatorService = validationService;
        this.controller = new MyTicketController(generationService, displayService);
    }

//    @Override
//    public CompletionStage<Optional<String>> processTransaction(Transaction tx) {
//        return CompletableFuture.supplyAsync(() -> transformTicket(tx))
//                .thenApply(transaction -> validateTicket(transaction))
//                .thenApply(transaction -> displayTicket(transaction));
//    }


    @Override
    public CompletionStage<Optional<String>> processTransaction(Transaction tx) {
        return CompletableFuture.supplyAsync(
                () -> controller.generateFromEmptyTransaction(tx))
                .thenApply(transaction -> validatorService.validateAndReturnTransaction(transaction))
                //TODO validation of ticket
                .thenApply(transaction -> displayService.getFormattedTransaction(transaction))
                ;
    }

    public CompletableFuture<Void> processCompletableFuture(Transaction tx) {
        return CompletableFuture.runAsync(() -> {
                    System.out.println("Running from " + Thread.currentThread().getName());
                });
    }

    public CompletableFuture<Optional<String>> processStringFuture(Transaction tx) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Running processStringFuture() from " + Thread.currentThread().getName());
            return Optional.of("Foo");
        });
    }

    public CompletionStage<Optional<String>> processCompletionStage(Transaction tx) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Running processCompletionStage() from " + Thread.currentThread().getName());
            return Optional.of("Foo");
        });
    }

    public CompletionStage<Optional<String>> processCompletionStage2(Transaction tx) {
        return CompletableFuture.supplyAsync(() -> {
            printBar();
            return Optional.of("Bar");
        });
    }

    public CompletionStage<Optional<String>> processCompletionStage3(Transaction tx) {
        return CompletableFuture.supplyAsync(this::getCar);
    }



    void printBar() {
        System.out.println("Running printBar() from " + Thread.currentThread().getName() + "...bar");
    }

    Optional<String> getCar () {
        return Optional.of("Car");
    }


    Optional<Transaction> transformTicket(Transaction transaction) {
        //TODO transform ticket
        return Optional.of(transaction);
    }

    Optional<String> validateTicket(Optional<Transaction> txOpt) {
        //TODO validate ticket
        return Optional.of(txOpt.get().toString());
    }

    Optional<String> displayTicket(Optional<String> txOpt) {
        //TODO display ticket
        System.out.println(txOpt.orElse("ERROR No transaction found"));
        return Optional.of(txOpt.get());
    }
}

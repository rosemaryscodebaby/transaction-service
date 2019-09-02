package com.bingobucket.ticket.service.impl;

import com.bingobucket.ticket.controller.MyTicketController;
import com.bingobucket.ticket.model.Transaction;
import com.bingobucket.ticket.service.DisplayService;
import com.bingobucket.ticket.service.GenerationService;
import com.bingobucket.ticket.service.Service;
import com.bingobucket.ticket.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Component
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

    @Override
    public CompletionStage<Optional<String>> processTransaction(Transaction tx) {
        return CompletableFuture.supplyAsync(
                () -> controller.generateFromEmptyTransaction(tx))
                .thenApply(transaction -> validatorService.validateAndReturnTransaction(transaction))
                .thenApply(transaction -> displayService.getFormattedTransaction(transaction));
    }

}

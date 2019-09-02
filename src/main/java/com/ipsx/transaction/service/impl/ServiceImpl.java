package com.ipsx.transaction.service.impl;

import com.ipsx.transaction.initializr.TicketFacade;
import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.service.DisplayService;
import com.ipsx.transaction.service.Service;
import com.ipsx.transaction.service.ValidationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;


@Component
@AllArgsConstructor
public class ServiceImpl implements Service {

    @Autowired
    private DisplayService displayService;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private TicketFacade controller;

    @Override
    public CompletionStage<Optional<String>> processTransaction(Transaction tx) {
        return CompletableFuture.supplyAsync(
                () -> controller.generateFromEmptyTransaction(tx))
                .thenApply(transaction -> validationService.validateAndReturnTransaction(transaction))
                .thenApply(transaction -> displayService.getFormattedTransaction(transaction));
    }

}

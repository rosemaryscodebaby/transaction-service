package com.ipsx.transaction.service.impl;

import com.ipsx.transaction.initializr.TicketFacade;
import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.service.DisplayService;
import com.ipsx.transaction.service.Service;
import com.ipsx.transaction.service.ValidationService;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceImplTest {

    private DisplayService displayService = new DisplayServiceImpl();
    private ValidationService validationService = new ValidationServiceImpl();
    private TicketFacade ticketFacade = new TicketFacade(new GenerationServiceImpl(), displayService);

    private Service sut = new ServiceImpl( displayService, validationService, ticketFacade);

    @Test
    void testProcessTransaction() throws Exception {
        final int numberOfTransactions = 500;
        System.out.println(String.format("VALID TRANSACTIONS FROM N=%d TRIES", numberOfTransactions));
        for(int x=0; x < numberOfTransactions; x++) {
            CompletionStage<Optional<String>> completionStage = sut.processTransaction(new Transaction());
            assertNotNull(completionStage);

            CompletableFuture<Optional<String>> result = completionStage.toCompletableFuture();
            result.join();

            assertTrue(result.isDone());

            if (result.get().isPresent()) {
                assertTrue(result.get().get().contains("Error"));
            }
        }
    }

}
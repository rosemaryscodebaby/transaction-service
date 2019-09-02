package com.bingobucket.ticket.service.impl;

import com.bingobucket.ticket.model.Transaction;
import com.bingobucket.ticket.service.Service;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceImplTest {

    private Service sut = new ServiceImpl(new GenerationServiceImpl(), new DisplayServiceImpl(), new ValidationServiceImpl());

    @Test
    void testProcessTransaction() throws Exception {
        final int numberOfTransactions = 500;
        System.out.println(String.format("VALID TRANSACTIONS FROM N=%d TRIES", numberOfTransactions));
        for(int x=0; x<numberOfTransactions; x++) {
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

    //TODO negative tests
}
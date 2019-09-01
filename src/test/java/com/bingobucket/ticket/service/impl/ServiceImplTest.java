package com.bingobucket.ticket.service.impl;

import com.bingobucket.ticket.model.Transaction;
import com.bingobucket.ticket.service.DisplayServiceImpl;
import com.bingobucket.ticket.service.GenerationServiceImpl;
import com.bingobucket.ticket.service.Service;
import com.bingobucket.ticket.service.ServiceImpl;
import com.bingobucket.ticket.service.TestDataProvider;
import com.bingobucket.ticket.service.ValidationServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

class ServiceImplTest extends TestDataProvider {

    private Service sut = new ServiceImpl(new GenerationServiceImpl(), new DisplayServiceImpl(), new ValidationServiceImpl());

    private Transaction invalidTx;

    private Transaction validTx;

    @Before
    public void setUp() {
        invalidTx = new Transaction();
        validTx.setData(buildTicketStrip());
    }


    @Test
    void testProcessCompletableFuture() {
        System.out.println(">> testProvessCompletableFuture");
        CompletableFuture<Void> result = sut.processCompletableFuture(validTx);
        assertNotNull(result);
        result.join();
        assertTrue(result.isDone());
        System.out.println("<< testProvessCompletableFuture");
    }

    @Test
    void testProcessCompletableStringFuture() throws Exception {
        System.out.println(">> testProcessCompletableStringFuture");
        CompletableFuture<Optional<String>> result = sut.processStringFuture(validTx);
        assertNotNull(result);
        result.join();
        assertTrue(result.isDone());
        assertEquals("Foo", result.get().get());
        System.out.println("<< testProcessCompletableStringFuture");
    }

    @Test
    void testProcessCompletionStage() throws Exception {
        System.out.println(">> testProcessCompletionStage");
        CompletionStage<Optional<String>> completionStage = sut.processCompletionStage(validTx);
        assertNotNull(completionStage);

        CompletableFuture<Optional<String>> result = completionStage.toCompletableFuture();
        result.join();

        assertTrue(result.isDone());
        assertEquals("Foo", result.get().get());
        System.out.println("<< testProcessCompletionStage");
    }

    @Test
    void testProcessCompletionStage2() throws Exception {
        System.out.println(">> testProcessCompletionStage2");
        CompletionStage<Optional<String>> completionStage = sut.processCompletionStage2(validTx);
        assertNotNull(completionStage);

        CompletableFuture<Optional<String>> result = completionStage.toCompletableFuture();
        result.join();

        assertTrue(result.isDone());
        assertEquals("Bar", result.get().get());
        System.out.println("<< testProcessCompletionStage2");
    }

    @Test
    void testProcessCompletionStage3() throws Exception {
        System.out.println(">> testProcessCompletionStage3");
        CompletionStage<Optional<String>> completionStage = sut.processCompletionStage3(validTx);
        assertNotNull(completionStage);

        CompletableFuture<Optional<String>> result = completionStage.toCompletableFuture();
        result.join();

        assertTrue(result.isDone());
        assertEquals( "Car", result.get().get());
        System.out.println("<< testProcessCompletionStage3");
    }

    @Test
    void testProcessCompletionStage4() throws Exception {
        System.out.println(">> testProcessCompletionStage4");
        CompletionStage<Optional<String>> completionStage = sut.processTransaction(new Transaction());
        assertNotNull(completionStage);

        CompletableFuture<Optional<String>> result = completionStage.toCompletableFuture();
        result.join();

        assertTrue(result.isDone());
        assertEquals( "Transaction", result.get().get());
        System.out.println("<< testProcessCompletionStage3");
    }
}
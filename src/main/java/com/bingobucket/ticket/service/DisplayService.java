package com.bingobucket.ticket.service;

import com.bingobucket.ticket.model.Transaction;

import java.util.Optional;


public interface DisplayService {

    void print(Transaction transaction);

    Optional<String> getFormattedTransaction(Optional<Transaction> transaction);
}

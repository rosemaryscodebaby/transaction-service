package com.ipsx.transaction.service;

import com.ipsx.transaction.model.Transaction;

import java.util.Optional;


public interface DisplayService {

    void print(Transaction transaction);

    Optional<String> getFormattedTransaction(Optional<Transaction> transaction);
}

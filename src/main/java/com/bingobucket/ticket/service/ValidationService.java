package com.bingobucket.ticket.service;

import com.bingobucket.ticket.model.Transaction;

import java.util.Optional;

public interface ValidationService {

    boolean validate(Transaction transaction);

    Optional<Transaction> validateAndReturnTransaction(Optional<Transaction> transaction);
}

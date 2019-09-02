package com.ipsx.transaction.service;

import com.ipsx.transaction.model.Transaction;

import java.util.Optional;

public interface ValidationService {

    boolean validate(Transaction transaction);

    Optional<Transaction> validateAndReturnTransaction(Optional<Transaction> transaction);
}

package com.ipsx.transaction.service;

import com.ipsx.transaction.model.Transaction;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface Service {

    /**
     *
     * @param tx an empty transaction i.e. a new Transaction() that has not been processed as of yet
     *           and has a tickets consisting of all zeros
     *           with the randomly dispersed series of blanks and random numbers yet to be assigned
     * @return
     */
    CompletionStage<Optional<String>> processTransaction(Transaction tx);
}

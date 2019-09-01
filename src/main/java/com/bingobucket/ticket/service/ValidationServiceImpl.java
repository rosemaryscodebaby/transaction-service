package com.bingobucket.ticket.service;

import com.bingobucket.ticket.model.Transaction;

import java.util.List;
import java.util.Optional;

import static com.bingobucket.ticket.configuration.Constants.BLANKS_PER_COLUMN;
import static com.bingobucket.ticket.configuration.Constants.COLUMN_LENGTH_OF_EACH_TICKET;
import static com.bingobucket.ticket.configuration.Constants.TOTAL_NUMBER_OF_TICKETS;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(Transaction transaction) {

        // Validate correct number of blanks
        for(Integer index : transaction.getData().keySet()) {
            if (!hasBingoStripWithCorrectNumberOfBlanksPerColumn(transaction.getData().get(index))) {
                return false;
            }
        }

        // Validate no ticket with column of all blanks
        for(Integer index : transaction.getData().keySet()) {
            if (!hasAtLeastOneNumberPerTicketColumn(transaction.getData().get(index))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<Transaction> validateAndReturnTransaction(Optional<Transaction> transaction) {

        // Validate correct number of blanks
        for(Integer index : transaction.get().getData().keySet()) {
            if (!hasBingoStripWithCorrectNumberOfBlanksPerColumn(transaction.get().getData().get(index))) {
                return Optional.empty();
            }
        }

        // Validate no ticket with column of all blanks
        for(Integer index : transaction.get().getData().keySet()) {
            if (!hasAtLeastOneNumberPerTicketColumn(transaction.get().getData().get(index))) {
                return Optional.empty();
            }
        }
        return transaction;
    }

    private boolean hasBingoStripWithCorrectNumberOfBlanksPerColumn(List<Integer> columnData) {
        return columnData.stream().filter(val -> val == -1).count() == BLANKS_PER_COLUMN;
    }

    private boolean hasAtLeastOneNumberPerTicketColumn(List<Integer> columnData) {
        for(int ticket = 0; ticket < TOTAL_NUMBER_OF_TICKETS; ticket++) {
            int index = ticket * COLUMN_LENGTH_OF_EACH_TICKET;

            if (columnData.subList(index, index + COLUMN_LENGTH_OF_EACH_TICKET).stream()
                    .filter(val -> val == -1).count() == COLUMN_LENGTH_OF_EACH_TICKET) {
                return false;
            }
        }
        return true;
    }
}

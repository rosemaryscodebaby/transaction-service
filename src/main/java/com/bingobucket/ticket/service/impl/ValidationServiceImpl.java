package com.bingobucket.ticket.service.impl;

import com.bingobucket.ticket.model.BingoStrip;
import com.bingobucket.ticket.service.ValidationService;

import java.util.List;

import static com.bingobucket.ticket.configuration.Constants.BLANKS_PER_COLUMN;
import static com.bingobucket.ticket.configuration.Constants.COLUMN_LENGTH_OF_EACH_TICKET;
import static com.bingobucket.ticket.configuration.Constants.TOTAL_NUMBER_OF_TICKETS;

public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean validate(BingoStrip bingoStrip) {

        // Validate correct number of blanks
        for(Integer index : bingoStrip.getData().keySet()) {
            if (!hasBingoStripWithCorrectNumberOfBlanksPerColumn(bingoStrip.getData().get(index))) {
                return false;
            }
        }

        // Validate no ticket with column of all blanks
        for(Integer index : bingoStrip.getData().keySet()) {
            if (!hasAtLeastOneNumberPerTicketColumn(bingoStrip.getData().get(index))) {
                return false;
            }
        }
        return true;
    }

    private boolean hasBingoStripWithCorrectNumberOfBlanksPerColumn(List<Integer> columnData) {
        return columnData.stream().filter(val -> val == -1).count() == BLANKS_PER_COLUMN;
    }

    private boolean hasAtLeastOneNumberPerTicketColumn(List<Integer> columnData) {
        for(int ticket = 0; ticket < TOTAL_NUMBER_OF_TICKETS; ticket++) {
            int index = ticket * COLUMN_LENGTH_OF_EACH_TICKET;

            if (columnData.subList(index, index + COLUMN_LENGTH_OF_EACH_TICKET).stream().filter(val -> val == -1).count() == COLUMN_LENGTH_OF_EACH_TICKET) {
                return false;
            }
        }
        return true;
    }
}

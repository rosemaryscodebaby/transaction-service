package com.ipsx.transaction.initializr;

import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.service.DisplayService;
import com.ipsx.transaction.service.GenerationService;
import com.ipsx.transaction.configuration.Constants;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class TicketFacade {

    @Autowired
    private GenerationService generationService;

    @Autowired
    private DisplayService displayService;


    public Optional<Transaction> generateMyTickets() {
        //Step 0 initialize to all 0s
        Transaction transaction = new Transaction();

        return generateFromEmptyTransaction(transaction);

    }

    public Optional<Transaction> generateFromEmptyTransaction(Transaction transaction) {
        //Step 1 assign empty spaces
        transaction = generationService.translateColumn(generationService.assignEmptySpaceToRow(transaction));

        //Step 2 assign numbers
        for(int c = 0; c < Constants.ROW_LENGTH_OF_EACH_TICKET; c++) {
            List<Integer> val = generationService.assignNumberToColumn(transaction.getData().get(c), c);
            transaction.setColumn(c, val);
        }
        return Optional.of(transaction);
    }

    public void printMyTickets(Transaction transaction) {
        displayService.print(transaction);
    }

}

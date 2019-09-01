package com.bingobucket.ticket.service;

import com.bingobucket.ticket.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

import static com.bingobucket.ticket.configuration.Constants.COLUMN_LENGTH_OF_EACH_TICKET;
import static com.bingobucket.ticket.configuration.Constants.COMBINED_COLUMN_LENGTH;

@Service
public class DisplayServiceImpl implements DisplayService {

    @Override
    public void print(Transaction transaction){
        IntStream.range(0, COMBINED_COLUMN_LENGTH).forEach(x -> doPrint(transaction.getData(), x));
    }

    @Override
    public Optional<String> getFormattedTransaction(Optional<Transaction> transaction) {
        System.out.println(">> getFormattedTransaction");
        //TODO unwrap optional if not empty, else return optional of error message
        System.out.println("<< getFormattedTransaction");
        return Optional.of("Transaction");
    }

    public void doPrint(Map<Integer, List<Integer>> data, int index) {
        printNewLineSeperator(index);
        System.out.println(String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",
                data.get(0).get(index).toString().replace("-1", "*"),
                data.get(1).get(index).toString().replace("-1", "*"),
                data.get(2).get(index).toString().replace("-1", "*"),
                data.get(3).get(index).toString().replace("-1", "*"),
                data.get(4).get(index).toString().replace("-1", "*"),
                data.get(5).get(index).toString().replace("-1", "*"),
                data.get(6).get(index).toString().replace("-1", "*"),
                data.get(7).get(index).toString().replace("-1", "*"),
                data.get(8).get(index).toString().replace("-1", "*")
        ));
    }



    private void printNewLineSeperator(int index) {
        if (index != 0 && index % COLUMN_LENGTH_OF_EACH_TICKET == 0) {
            System.out.println();
        }
    }

}

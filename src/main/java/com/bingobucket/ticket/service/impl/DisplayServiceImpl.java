package com.bingobucket.ticket.service.impl;

import com.bingobucket.ticket.model.Transaction;
import com.bingobucket.ticket.service.DisplayService;
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
        printHeader();
        IntStream.range(0, COMBINED_COLUMN_LENGTH).forEach(x -> doPrint(transaction.getData(), x));
        pringFooter();
    }

    /**
     * Print ONLY valid transactions, with an error message (or lack thereof) being returned
     * @param transaction a mature transaction which may or may not be valid
     * which should always be present, and will fail with an exception if given param is empty
     * @return an empty optional if transaction is valid,
     *  otherwise an error message if transaction failed to validate
     */
    @Override
    public Optional<String> getFormattedTransaction(Optional<Transaction> transaction) {
        //TODO unwrap optional if not empty, else return optional of error message
        if(transaction.isPresent()) {
            Optional<String> errMsg = transaction.get().getErrorMsg();
            if(!errMsg.isPresent()) {
                //transaction is ok
                print(transaction.get());
            }
            return errMsg;
        }
        throw new RuntimeException("Exception:transaction could not be found");
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

    private void printHeader() {
        System.out.println("--B----i----n----g----o---9----0--");
    }

    private void pringFooter() {
        System.out.println("----------------------------------");
    }

}

package com.bingobucket.ticket.service.impl;

import com.bingobucket.ticket.service.DisplayService;

import static com.bingobucket.ticket.configuration.Constants.COLUMN_LENGTH_OF_EACH_TICKET;

import java.util.List;
import java.util.Map;

public class DisplayServiceImpl implements DisplayService {

    @Override
    public void print(Map<Integer, List<Integer>> data, int index) {
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

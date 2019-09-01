package com.bingobucket.ticket.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bingobucket.ticket.configuration.Constants.COLUMN_INDEXES;
import static com.bingobucket.ticket.configuration.Constants.COMBINED_COLUMN_LENGTH;

public class BingoStrip {
    private Map<Integer, List<Integer>> data = new HashMap<>();

    public BingoStrip() {
        Arrays.stream(COLUMN_INDEXES).forEach(x -> data.put(Integer.valueOf(x), new ArrayList<>(Collections.nCopies(COMBINED_COLUMN_LENGTH, 0))));
    }

    public Map<Integer, List<Integer>> getData() {
        return data;
    }

    public void setData(Map<Integer, List<Integer>> data) {
        this.data = data;
    }

    public void setColumn(int index, List<Integer> columnData) {
        data.put(index, columnData);
    }

    public void assignEmptyArrByRow(int colNum, int rowNum) {
        data.get(colNum).set(rowNum, -1);
    }

}

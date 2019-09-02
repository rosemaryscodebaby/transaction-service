package com.ipsx.transaction.model;

import static com.ipsx.transaction.configuration.Constants.COLUMN_INDEXES;
import static com.ipsx.transaction.configuration.Constants.COMBINED_COLUMN_LENGTH;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Transaction {
    private Map<Integer, List<Integer>> data = new HashMap<>();

    private String errMsg;

    public Transaction() {
        Arrays.stream(COLUMN_INDEXES).forEach(x -> data.put(Integer.valueOf(x), new ArrayList<>(Collections.nCopies(COMBINED_COLUMN_LENGTH, 0))));
    }

    public Transaction(String errMsg) {
        this.errMsg = errMsg;
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

    public Optional<String> getErrorMsg() {
        return Optional.ofNullable(errMsg);
    }

}

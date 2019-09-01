package com.bingobucket.ticket.util;

import com.bingobucket.ticket.model.Transaction;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.bingobucket.ticket.configuration.Constants.COLUMN_LENGTH_OF_EACH_TICKET;
import static com.bingobucket.ticket.configuration.Constants.COMBINED_COLUMN_LENGTH;

public class ServiceUtil {

    public static Integer[] getRandomNumberArr(int n, int maxRange) {
        assert n <= maxRange : "cannot get more unique numbers than the size of the range";

        Integer[] result = new Integer[n];
        Set<Integer> used = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {

            int newRandom;
            do {
                newRandom = getRandomNumber(0, maxRange);
            } while (used.contains(newRandom));
            result[i] = newRandom;
            used.add(newRandom);
        }
        return result;
    }


    public static Map<Integer, List<Integer>> shiftBlanks(Transaction transaction, int colNum, long excess, int swapVal) {
        // perform swaps on each row from top-down
        List<Integer> nextCol = transaction.getData().get(colNum + 1);
        List<Integer> thisCol = transaction.getData().get(colNum);

        for(int i = 0; i < COMBINED_COLUMN_LENGTH; i++) {
            if(swapVal == 0){
                // purge blanks from thisCol
                excess = ServiceUtil.performSwap(i, thisCol, nextCol, excess, swapVal);
            } else {
                // acquire more blanks to thisCol
                excess = ServiceUtil.performSwap(i, nextCol, thisCol, excess, swapVal);
            }
        }
        transaction.setColumn(colNum, thisCol);
        transaction.setColumn(colNum + 1, nextCol);
        return transaction.getData();
    }


    public static Transaction doAssignEmptySpaceToRow(Transaction transaction, int rowNum, Integer... indxArr) {
        Arrays.stream(indxArr).forEach(i -> transaction.assignEmptyArrByRow(i, rowNum));
        return transaction;
    }

    private static long performSwap(int i, List<Integer> thisCol, List<Integer> nextCol, long excess, int swapVal) {
        if ( (i % COLUMN_LENGTH_OF_EACH_TICKET == 0)
                && (i + COLUMN_LENGTH_OF_EACH_TICKET) < COMBINED_COLUMN_LENGTH
                && (nextCol.subList(i, (i + COLUMN_LENGTH_OF_EACH_TICKET))).stream().filter(val -> val == -1).count() >= 2) {
            return excess;// skip swap when two rows are already empty to avoid a rule violation
        }

        if ((excess > 0) && (thisCol.get(i) != swapVal && nextCol.get(i) == 0)) {//purge swap
            int tempVal = thisCol.get(i);
            thisCol.set(i, nextCol.get(i));
            nextCol.set(i, tempVal);
            excess--;
        }
        return excess;
    }


    public static List<Integer> assignValueToEmptyCell(int low, int high, List<Integer> column) {
        Integer[] columnArr = column.toArray(new Integer[column.size()]);
        List<Integer> availableIndexes = IntStream
                .range(0, column.size())
                .filter(i -> columnArr[i] == 0)
                .mapToObj(i -> i)
                .collect(Collectors.toList());

        Collections.shuffle(availableIndexes);
        for(int indx: availableIndexes) {
            column.set(indx, low);
            low++;
        }
        return column;
    }


    private static int getRandomNumber(int lowerBound, int upperBound) {
        return ThreadLocalRandom.current().nextInt(lowerBound, upperBound);
    }


}

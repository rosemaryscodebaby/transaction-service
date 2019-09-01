package com.bingobucket.ticket.service.impl;

import static com.bingobucket.ticket.configuration.Constants.BLANKS_PER_COLUMN;
import static com.bingobucket.ticket.configuration.Constants.BLANKS_PER_ROW;
import static com.bingobucket.ticket.configuration.Constants.COLUMN_LENGTH_OF_EACH_TICKET;
import static com.bingobucket.ticket.configuration.Constants.NUMBERS_PER_COLUMN;
import static com.bingobucket.ticket.configuration.Constants.ROW_LENGTH_OF_EACH_TICKET;
import static com.bingobucket.ticket.configuration.Constants.TOTAL_NUMBER_OF_TICKETS;

import com.bingobucket.ticket.model.BingoStrip;
import com.bingobucket.ticket.service.GenerationService;
import com.bingobucket.ticket.util.ServiceUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class GenerationServiceImpl implements GenerationService {

    @Override
    public BingoStrip assignEmptySpaceToRow(BingoStrip bingoStrip) {
        BingoStrip result = bingoStrip;
        for(int i=0; i<TOTAL_NUMBER_OF_TICKETS; i++) {
            result = doAssignEmptySpaceToRow(i, result);
        }
        return result;
    }

    @Override
    public BingoStrip translateColumn(BingoStrip bingoStrip) {
        BingoStrip result = bingoStrip;
        // Perform swaps on all columns except the last one
        for(int colNum = 0; colNum < ROW_LENGTH_OF_EACH_TICKET - 1; colNum++) {
            result = performSwap(bingoStrip, colNum, true);
        }
        // Perform swap on last column but in opposite direction and return result
        return performSwap(result, ROW_LENGTH_OF_EACH_TICKET - 2, false);
    }

    @Override
    public List<Integer> assignNumberToColumn(List<Integer> column, int columnIndex) {
        int high = (columnIndex + 1) * NUMBERS_PER_COLUMN + 1;
        int low = (high - NUMBERS_PER_COLUMN);
        return ServiceUtil.assignValueToEmptyCell(low, high, column);
    }

    private BingoStrip performSwap(BingoStrip result, int colNum, boolean isForwardSwap) {
        long emptyCount = result.getData().get(colNum).stream().filter(v -> v == -1).count();
        if (emptyCount > BLANKS_PER_COLUMN) {
            long diff = Math.abs(BLANKS_PER_COLUMN - emptyCount);
            result.setData(ServiceUtil.shiftBlanks(result, colNum, diff, isForwardSwap ? 0 : -1));
        } else if (emptyCount < BLANKS_PER_COLUMN) {
            long diff = Math.abs(BLANKS_PER_COLUMN - emptyCount);
            result.setData(ServiceUtil.shiftBlanks(result, colNum, diff, isForwardSwap ? -1 : 0));
        }
        return result;
    }

    private BingoStrip doAssignEmptySpaceToRow(int i, BingoStrip bingoStrip) {
        BingoStrip result;
        int index = i * COLUMN_LENGTH_OF_EACH_TICKET;

        Integer[] firstBlankRow = ServiceUtil.getRandomNumberArr(BLANKS_PER_ROW, ROW_LENGTH_OF_EACH_TICKET);
        result = ServiceUtil.doAssignEmptySpaceToRow(bingoStrip, index, firstBlankRow);

        Integer[] secondBlankRow = ServiceUtil.getRandomNumberArr(BLANKS_PER_ROW, ROW_LENGTH_OF_EACH_TICKET);
        result = ServiceUtil.doAssignEmptySpaceToRow(result, index + 1, secondBlankRow);

        List<Integer> availableIndexes = new ArrayList<>();
        for(int cnt = 0; cnt< ROW_LENGTH_OF_EACH_TICKET; cnt++) {
            if(!(Arrays.asList(firstBlankRow).contains(cnt) && Arrays.asList(secondBlankRow).contains(cnt))){
                availableIndexes.add(cnt);
            }
        }
        truncateToFourRandomElements(availableIndexes);
        Integer[] thirdBlankRow = availableIndexes.stream().toArray(Integer[]::new);
        return ServiceUtil.doAssignEmptySpaceToRow(result, index + 2, thirdBlankRow);
    }

    private void truncateToFourRandomElements(List<Integer> availableIndexes) {
        Random rand = new Random();
        int numberOfElements = BLANKS_PER_ROW;

        while(availableIndexes.size() > numberOfElements) {
            int randomIndex = rand.nextInt(availableIndexes.size());
            availableIndexes.remove(randomIndex);
        }
    }

}

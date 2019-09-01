package com.bingobucket.ticket.service;

import com.bingobucket.ticket.model.BingoStrip;

import java.util.List;

public interface GenerationService {

    BingoStrip assignEmptySpaceToRow(BingoStrip data);

    BingoStrip translateColumn(BingoStrip data);

    List<Integer> assignNumberToColumn(List<Integer> column, int index);
}

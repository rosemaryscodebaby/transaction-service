package com.bingobucket.ticket.service;

import com.bingobucket.ticket.model.Transaction;

import java.util.List;

public interface GenerationService {

    Transaction assignEmptySpaceToRow(Transaction data);

    Transaction translateColumn(Transaction data);

    List<Integer> assignNumberToColumn(List<Integer> column, int index);
}

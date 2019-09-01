package com.bingobucket.ticket.service;

import com.bingobucket.ticket.model.BingoStrip;

public interface ValidationService {

    boolean validate(BingoStrip data);
}

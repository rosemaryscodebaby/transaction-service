package com.bingobucket.ticket.controller;

import com.bingobucket.ticket.model.BingoStrip;
import com.bingobucket.ticket.service.DisplayService;
import com.bingobucket.ticket.service.GenerationService;

import java.util.List;
import java.util.stream.IntStream;

import static com.bingobucket.ticket.configuration.Constants.COMBINED_COLUMN_LENGTH;
import static com.bingobucket.ticket.configuration.Constants.ROW_LENGTH_OF_EACH_TICKET;

public class MyTicketController {

    private GenerationService generationService;

    private DisplayService displayService;

    public MyTicketController(GenerationService generationService, DisplayService ticketDisplayService) {
        this.generationService = generationService;
        this.displayService = ticketDisplayService;
    }

    public BingoStrip generateMyTickets() {
        BingoStrip bingoStrip = new BingoStrip();

        bingoStrip = generationService.translateColumn(generationService.assignEmptySpaceToRow(bingoStrip));

        for(int c = 0; c < ROW_LENGTH_OF_EACH_TICKET; c++) {
            List<Integer> val = generationService.assignNumberToColumn(bingoStrip.getData().get(c), c);
            bingoStrip.setColumn(c, val);
        }
        return bingoStrip;
    }

    public void printMyTickets(BingoStrip bingoStrip) {
        IntStream.range(0, COMBINED_COLUMN_LENGTH).forEach(x -> displayService.print(bingoStrip.getData(), x));
    }

}

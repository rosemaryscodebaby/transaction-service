package com.bingobucket.ticket.service.impl;

import com.bingobucket.ticket.model.Transaction;
import com.bingobucket.ticket.service.GenerationService;
import com.bingobucket.ticket.service.GenerationServiceImpl;
import com.bingobucket.ticket.service.TestDataProvider;
import org.junit.jupiter.api.Test;

import static com.bingobucket.ticket.configuration.Constants.COLUMN_INDEXES;
import static com.bingobucket.ticket.configuration.Constants.COMBINED_COLUMN_LENGTH;
import static org.junit.Assert.*;

import java.util.List;

public class GenerationServiceImplTest extends TestDataProvider {

    private GenerationService sut = new GenerationServiceImpl();

    @Test
    public void testAssignEmptySpaceToRow() {
        Transaction bingoStrip = new Transaction();
        sut.assignEmptySpaceToRow(bingoStrip);

        assertEquals(bingoStrip.getData().size(), COLUMN_INDEXES.length);
        assertEquals(bingoStrip.getData().get(0).size(), COMBINED_COLUMN_LENGTH);
    }

    //!@Test
    public void testTranslateColumn() {
        Transaction bingoStrip = new Transaction();
        bingoStrip.setData(buildTicketStrip());

        sut.translateColumn(bingoStrip);
        assertEquals(bingoStrip.getData().size(), COLUMN_INDEXES.length);
        assertEquals(bingoStrip.getData().get(0).size(), COMBINED_COLUMN_LENGTH);
    }

    //!@Test
    public void testAssignNumberToColumnAlreadyCorrect() {
        Transaction bingoStrip = new Transaction();
        bingoStrip.setData(buildTicketStrip());

        List<Integer> expect = super.list2;
        List<Integer> actual = sut.assignNumberToColumn(expect, 2);

        assertArrayEquals(expect.toArray(), actual.toArray());
    }

}
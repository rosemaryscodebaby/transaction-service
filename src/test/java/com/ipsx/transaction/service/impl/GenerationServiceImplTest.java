package com.ipsx.transaction.service.impl;

import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.service.GenerationService;
import com.ipsx.transaction.service.TestDataProvider;
import org.junit.jupiter.api.Test;

import static com.ipsx.transaction.configuration.Constants.COLUMN_INDEXES;
import static com.ipsx.transaction.configuration.Constants.COMBINED_COLUMN_LENGTH;
import static org.junit.Assert.assertEquals;

public class GenerationServiceImplTest extends TestDataProvider {

    private GenerationService sut = new GenerationServiceImpl();

    @Test
    public void testAssignEmptySpaceToRow() {
        Transaction bingoStrip = new Transaction();
        sut.assignEmptySpaceToRow(bingoStrip);

        assertEquals(bingoStrip.getData().size(), COLUMN_INDEXES.length);
        assertEquals(bingoStrip.getData().get(0).size(), COMBINED_COLUMN_LENGTH);
    }

}
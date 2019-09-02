package com.ipsx.transaction.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ipsx.transaction.model.Transaction;
import com.ipsx.transaction.service.TestDataProvider;
import com.ipsx.transaction.service.ValidationService;
import org.junit.Test;

public class ValidationServiceImplTest extends TestDataProvider {

    private ValidationService sut = new ValidationServiceImpl();

    @Test
    public void isNotValidOnInitialCreation() {
        Transaction bingoStrip = new Transaction();
        assertFalse(sut.validate(bingoStrip));
    }

    @Test
    public void isValid() {
        Transaction bingoStrip = new Transaction();
        bingoStrip.setData(buildTicketStrip());
        assertTrue(sut.validate(bingoStrip));
    }

    @Test
    public void isNotValidTooManyBlanks() {
        Transaction bingoStrip = new Transaction();
        list1.set(2, -1);
        bingoStrip.setData(buildTicketStrip());
        assertFalse(sut.validate(bingoStrip));
    }

    @Test
    public void isNotValidThreeBlanksOnATicketColumn() {
        Transaction bingoStrip = new Transaction();
        list0.set(16, -1);
        list0.set(0, 3);
        bingoStrip.setData(buildTicketStrip());
        assertFalse(sut.validate(bingoStrip));
    }
}
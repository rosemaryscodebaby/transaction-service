package com.ipsx.transaction.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TicketTest {

    private final Ticket sut = new Ticket();

    @Test
    void testGetId() {
        final String expect = "5d6c96a096ed123807110e55";
        assertNull(sut.getId());
        sut.setId(expect);
        assertEquals(expect, sut.getId());
    }

    @Test
    void testGetGroupId() {
        final String expect = UUID.randomUUID().toString();
        assertNull(sut.getGroupId());
        sut.setGroupId(expect);
        assertEquals(expect, sut.getGroupId());
    }

    @Test
    void testGetValue() {
        final String expect = "10 * * 40 * 60 69 * 86\\n* 12 28 38 46 * * 78 *\\n* * 30 35 47 56 * 76 *";
        assertNull(sut.getValue());
        sut.setValue(expect);
        assertEquals(expect, sut.getValue());
    }
}
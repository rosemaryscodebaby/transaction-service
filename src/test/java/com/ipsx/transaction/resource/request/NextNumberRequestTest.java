package com.ipsx.transaction.resource.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextNumberRequestTest {

    private final NextNumberRequest sut = new NextNumberRequest();

    @Test
    void testGetName() {
        final String expect = "Sally";
        assertNull(sut.getName());
        sut.setName(expect);
        assertEquals(expect, sut.getName());
    }
}
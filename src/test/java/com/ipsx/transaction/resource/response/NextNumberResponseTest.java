package com.ipsx.transaction.resource.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NextNumberResponseTest {

    private final NextNumberResponse sut = new NextNumberResponse();

    @Test
    void getMessage() {
        final String expect = "Hello, Test!";
        assertNull(sut.getMessage());
        sut.setMessage(expect);
        assertEquals(expect, sut.getMessage());
        assertEquals("Another, message", new NextNumberResponse("Another, message").getMessage());
    }
}
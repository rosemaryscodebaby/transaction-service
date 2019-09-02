package com.ipsx.transaction.configuration;

import com.ipsx.transaction.resource.request.NextNumberRequest;
import com.ipsx.transaction.resource.response.NextNumberResponse;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class NumberProducerTest {

    private NextNumberRequest nextNumberRequest = new NextNumberRequest("James");

    private NumberProducer sut = new NumberProducer();

    @Test
    void revealNextNumber() {
        Flux<NextNumberResponse> actual = sut.revealNextNumber(nextNumberRequest);
        assertNotNull(actual);
    }
}
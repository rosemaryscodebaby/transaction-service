package com.bingobucket.ticket.configuration;

import com.bingobucket.ticket.resource.request.GreetingRequest;
import com.bingobucket.ticket.resource.response.GreetingResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.Stream;

/**
 * Push data to the client via a subscription
 */
@Component
class GreetingProducer {
    Flux<GreetingResponse> greet (GreetingRequest greetingRequest) {
        return Flux.fromStream(Stream.generate(
                () -> new GreetingResponse("hello" + greetingRequest.getName() +  " @ " + Instant.now() + "!")))
                .delayElements(Duration.ofSeconds(1));
    }
}

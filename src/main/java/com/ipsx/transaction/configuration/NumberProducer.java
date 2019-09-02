package com.ipsx.transaction.configuration;

import com.ipsx.transaction.resource.request.NextNumberRequest;
import com.ipsx.transaction.resource.response.NextNumberResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Push data to the client via a subscription
 * Can be accessed from the index.html page
 */
@Component
class NumberProducer {
    Flux<NextNumberResponse> revealNextNumber (NextNumberRequest nextNumberRequest) {

        //For demo purposes only
        //TODO wait for enough players to join, then count down to next simulation
        //TODO get numbers from database where they can be randomly set one-by-one in realtime via a microservice
        final int min = 1;
        final int max = 90;
        List<Integer> range = IntStream.rangeClosed(min, max).boxed().collect(Collectors.toList());
        Collections.shuffle(range);

        return Flux.fromStream(Stream.generate(
                () -> new NextNumberResponse("Welcome " + nextNumberRequest.getName() +  ", The latest number is "
                        + " <b>" + range.get((int) Instant.now().getEpochSecond() % max) + "</b>. "
                        + "Stay tuned for the next number..." + "\n")))
                .delayElements(Duration.ofSeconds(3));
    }
}

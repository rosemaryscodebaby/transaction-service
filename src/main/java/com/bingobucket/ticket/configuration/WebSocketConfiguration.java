package com.bingobucket.ticket.configuration;

import com.bingobucket.ticket.resource.request.GreetingRequest;
import com.bingobucket.ticket.resource.response.GreetingResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.Map;

@Configuration
public class WebSocketConfiguration {

    @Bean
    SimpleUrlHandlerMapping simpleUrlHandlerMapping(WebSocketHandler wsh) {
        return new SimpleUrlHandlerMapping() {
            {
                setUrlMap(Map.of("/ws/greetings", wsh));
                setOrder(10);
            }
        };
    }

    @Bean
    WebSocketHandlerAdapter webSocketHandlerAdapter() {
        return new WebSocketHandlerAdapter() {
        };
    }

    @Bean
    WebSocketHandler webSocketHandler(GreetingProducer gp) {
        return session -> {
            var response =
                    session
                            .receive()
                            .map(WebSocketMessage::getPayloadAsText)
                            .map(GreetingRequest::new)
                            .flatMap(gp::greet)
                            .map(GreetingResponse::getMessage)
                            .map(session::textMessage);
            return session.send(response);
        };
    }
}


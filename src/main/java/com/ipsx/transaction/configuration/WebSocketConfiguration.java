package com.ipsx.transaction.configuration;

import com.ipsx.transaction.resource.request.NextNumberRequest;
import com.ipsx.transaction.resource.response.NextNumberResponse;
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
                setUrlMap(Map.of("/index/bingo90", wsh));
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
    WebSocketHandler webSocketHandler(NumberProducer gp) {
        return session -> {
            var response =
                    session
                            .receive()
                            .map(WebSocketMessage::getPayloadAsText)
                            .map(NextNumberRequest::new)
                            .flatMap(gp::revealNextNumber)
                            .map(NextNumberResponse::getMessage)
                            .map(session::textMessage);
            return session.send(response);
        };
    }
}


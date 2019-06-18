package com.github.pedrobacchini.webflux.config;

import com.github.pedrobacchini.webflux.resource.PlayListHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class PlayListRouter {

    @Bean
    public RouterFunction<ServerResponse> route(PlayListHandler playListHandler) {
        return RouterFunctions
                .route(GET("/playlist").and(accept(MediaType.APPLICATION_JSON)), playListHandler::findAllPlayList)
                .andRoute(GET("/playlist/{id}").and(accept(MediaType.APPLICATION_JSON)), playListHandler::findById)
                .andRoute(POST("/playlist").and(accept(MediaType.APPLICATION_JSON)), playListHandler::save);
    }
}

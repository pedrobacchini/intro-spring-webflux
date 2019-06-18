package com.github.pedrobacchini.webflux.resource;

import com.github.pedrobacchini.webflux.document.Playlist;
import com.github.pedrobacchini.webflux.service.PlayListService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

//@Component
public class PlayListHandler {

    private final PlayListService playListService;

    public PlayListHandler(PlayListService playListService) { this.playListService = playListService; }

    public Mono<ServerResponse> findAllPlayList(ServerRequest serverRequest) {
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playListService.findAll(), Playlist.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        String id = serverRequest.pathVariable("id");
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(playListService.findById(id), Playlist.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Playlist> playlistMono = serverRequest.bodyToMono(Playlist.class);
        return ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromPublisher(playlistMono.flatMap(playListService::save), Playlist.class));
    }
}

package com.github.pedrobacchini.webflux.resource;

import com.github.pedrobacchini.webflux.document.Playlist;
import com.github.pedrobacchini.webflux.service.PlayListService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;

@RestController
@RequestMapping("/playlist")
public class PlayListResource {

    private final PlayListService playListService;

    public PlayListResource(PlayListService playListService) { this.playListService = playListService; }

    @GetMapping
    public Flux<Playlist> getAllPlaylist() { return playListService.findAll(); }

    @GetMapping("/{id}")
    public Mono<Playlist> getPlaylist(@PathVariable String id) { return playListService.findById(id); }

    @PostMapping
    public Mono<Playlist> savePlayList(@RequestBody Playlist playlist) { return playListService.save(playlist); }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Tuple2<Long, Playlist>> getPlaylistByEvents() {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(10));
        Flux<Playlist> events = playListService.findAll();
        System.out.println("passou aqui");
        return Flux.zip(interval, events);
    }
}

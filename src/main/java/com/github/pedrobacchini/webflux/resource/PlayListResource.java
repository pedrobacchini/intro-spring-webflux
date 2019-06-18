package com.github.pedrobacchini.webflux.resource;

import com.github.pedrobacchini.webflux.document.Playlist;
import com.github.pedrobacchini.webflux.service.PlayListService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
//@RequestMapping("/playlist")
public class PlayListResource {

    private final PlayListService playListService;

    public PlayListResource(PlayListService playListService) { this.playListService = playListService; }

    @GetMapping
    public Flux<Playlist> getAllPlaylist() { return playListService.findAll(); }

    @GetMapping("/{id}")
    public Mono<Playlist> getPlaylist(@PathVariable String id) { return playListService.findById(id); }

    @PostMapping
    public Mono<Playlist> savePlayList(@RequestBody Playlist playlist) { return playListService.save(playlist); }
}

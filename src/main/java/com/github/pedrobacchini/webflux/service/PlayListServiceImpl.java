package com.github.pedrobacchini.webflux.service;

import com.github.pedrobacchini.webflux.document.Playlist;
import com.github.pedrobacchini.webflux.repository.PlayListRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayListServiceImpl implements PlayListService {

    private final PlayListRepository playListRepository;

    public PlayListServiceImpl(PlayListRepository playListRepository) { this.playListRepository = playListRepository; }

    @Override
    public Flux<Playlist> findAll() {
        return playListRepository.findAll();
    }

    @Override
    public Mono<Playlist> findById(String id) {
        return playListRepository.findById(id);
    }

    @Override
    public Mono<Playlist> save(Playlist playlist) {
        return playListRepository.save(playlist);
    }
}

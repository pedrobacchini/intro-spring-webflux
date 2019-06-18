package com.github.pedrobacchini.webflux.repository;

import com.github.pedrobacchini.webflux.document.Playlist;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayListRepository extends ReactiveMongoRepository<Playlist, String> {
}

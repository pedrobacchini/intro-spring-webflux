package com.github.pedrobacchini.webflux.config;

import com.github.pedrobacchini.webflux.document.Playlist;
import com.github.pedrobacchini.webflux.repository.PlayListRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Configuration
public class Instantiation implements CommandLineRunner {

    private final PlayListRepository playListRepository;

    public Instantiation(PlayListRepository playListRepository) { this.playListRepository = playListRepository; }

    @Override
    public void run(String... args) {
        playListRepository.deleteAll()
                .thenMany(
                        Flux.just("Blues Rock Roadtrip", "Outono Acústico", "Sexy Party Hits", "A Pop Love Story",
                        "Cook & Chill", "Jantar a Dois", "Vem pro Sertanejo", "1000 Volts of Reggaeton", "Gospel Top Hits")
                        .map(nome -> new Playlist(UUID.randomUUID().toString(), nome))
                        .flatMap(playListRepository::save))
                .subscribe(System.out::println);
    }
}

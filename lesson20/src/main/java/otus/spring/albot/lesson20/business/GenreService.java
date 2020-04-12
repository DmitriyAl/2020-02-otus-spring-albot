package otus.spring.albot.lesson20.business;

import otus.spring.albot.lesson20.entity.Genre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GenreService {
    Flux<Genre> findAllGenres();

    Mono<Genre> findByName(String name);

    Mono<Genre> addNewGenre(Genre genre);

    Mono<Void> removeGenreById(String id);
}

package otus.spring.albot.lesson20.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import otus.spring.albot.lesson20.entity.Genre;
import reactor.core.publisher.Mono;

public interface GenreRepo extends ReactiveMongoRepository<Genre, String> {
    Mono<Genre> findByName(String name);
}

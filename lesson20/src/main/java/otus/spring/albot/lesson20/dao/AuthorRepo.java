package otus.spring.albot.lesson20.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import otus.spring.albot.lesson20.entity.Author;
import reactor.core.publisher.Mono;

public interface AuthorRepo extends ReactiveMongoRepository<Author, String> {
    Mono<Author> findByName(String name);
}

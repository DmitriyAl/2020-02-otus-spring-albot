package otus.spring.albot.lesson20.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import otus.spring.albot.lesson20.entity.Author;
import otus.spring.albot.lesson20.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookRepo extends ReactiveMongoRepository<Book, String> {
    Mono<Book> findByName(String name);

    Flux<Book> findAllByAuthor(Author author);
}

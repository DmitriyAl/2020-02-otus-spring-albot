package otus.spring.albot.lesson20.business;

import otus.spring.albot.lesson20.entity.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthorService {
    Flux<Author> findAllAuthors();

    Mono<Author> saveNewAuthor(Author author);

    Mono<Author> findByName(String name);

    Mono<Void> removeAuthorById(String id);

    Mono<Author> changeAuthorName(Author author);
}

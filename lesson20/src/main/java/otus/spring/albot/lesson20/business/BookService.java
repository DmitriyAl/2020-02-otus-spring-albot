package otus.spring.albot.lesson20.business;

import otus.spring.albot.lesson20.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {
    Flux<Book> findAllBooks();

    Mono<Book> findByName(String name);

    Mono<Book> findById(String id);

    Flux<Book> findAllBooksByAuthorId(String id);

    Mono<Book> addNewBook(String name, String authorId, String genreId);

    Mono<Void> removeBookById(String id);
}

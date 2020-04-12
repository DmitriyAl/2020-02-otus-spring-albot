package otus.spring.albot.lesson20.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Note;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NoteRepo extends ReactiveMongoRepository<Note, String> {
    Flux<Note> findByBook(Mono<Book> book);
}

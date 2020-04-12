package otus.spring.albot.lesson20.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Note;
import reactor.core.publisher.Flux;

public interface NoteRepo extends ReactiveMongoRepository<Note, String> {
    Flux<Note> findByBook(Book book);
}

package otus.spring.albot.lesson13.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.entity.Note;

import java.util.List;

public interface NoteRepo extends MongoRepository<Note, String> {
    List<Note> findByBook(Book book);
}

package otus.spring.albot.lesson25.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson25.document.BookDoc;
import otus.spring.albot.lesson25.document.NoteDoc;

import java.util.List;

public interface NoteRepo extends MongoRepository<NoteDoc, String> {
    List<NoteDoc> findByBook(BookDoc book);
}

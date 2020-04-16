package otus.spring.albot.lesson25.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson25.document.BookDoc;

import java.util.Optional;

public interface BookRepo extends MongoRepository<BookDoc, String> {
    Optional<BookDoc> findByName(String name);
}

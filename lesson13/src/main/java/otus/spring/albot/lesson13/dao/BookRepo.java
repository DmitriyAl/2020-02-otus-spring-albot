package otus.spring.albot.lesson13.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson13.entity.Book;

import java.util.Optional;

public interface BookRepo extends MongoRepository<Book, String> {
    Optional<Book> findByName(String name);
}

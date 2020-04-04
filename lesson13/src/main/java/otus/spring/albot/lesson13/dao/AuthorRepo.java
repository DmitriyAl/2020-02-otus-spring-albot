package otus.spring.albot.lesson13.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson13.entity.Author;

import java.util.Optional;

public interface AuthorRepo extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}

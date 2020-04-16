package otus.spring.albot.lesson25.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson25.document.GenreDoc;

import java.util.Optional;

public interface GenreRepo extends MongoRepository<GenreDoc, String> {
    Optional<GenreDoc> findByName(String name);
}

package otus.spring.albot.lesson13.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson13.entity.Genre;

import java.util.Optional;

public interface GenreRepo extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}

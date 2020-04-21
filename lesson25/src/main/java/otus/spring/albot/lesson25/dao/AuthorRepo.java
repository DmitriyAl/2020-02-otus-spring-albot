package otus.spring.albot.lesson25.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import otus.spring.albot.lesson25.document.AuthorDoc;

import java.util.Optional;

public interface AuthorRepo extends MongoRepository<AuthorDoc, String> {
    Optional<AuthorDoc> findByName(String name);
}

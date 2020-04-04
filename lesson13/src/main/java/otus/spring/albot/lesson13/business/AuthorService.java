package otus.spring.albot.lesson13.business;

import otus.spring.albot.lesson13.entity.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthors();

    Author saveNewAuthor(String name);

    Author findByName(String name);

    void removeAuthorById(String id);

    Author changeAuthorName(String id, String newName);
}

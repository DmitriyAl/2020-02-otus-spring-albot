package otus.spring.albot.lesson11.business;

import otus.spring.albot.lesson11.entity.Author;

import java.util.List;

/**
 * <pre>
 * $Id: $
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * </pre>
 *
 * @author Dmitrii Albot
 */
public interface AuthorService {
    List<Author> findAllAuthors();

    Author saveNewAuthor(String name);

    Author findByName(String name);

    boolean removeAuthorById(long id);

    Author changeAuthorName(long id, String newName);
}
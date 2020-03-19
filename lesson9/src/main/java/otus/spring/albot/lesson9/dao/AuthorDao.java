package otus.spring.albot.lesson9.dao;

import otus.spring.albot.lesson9.entity.Author;

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
public interface AuthorDao {
    List<Author> findAllAuthors();

    List<Author> findAuthorByTemplate(String template);

    Author findById(long id);

    void addNewAuthor(String name);

    void deleteAuthor(long id);

    Author changeAuthorName(long id, String newName);
}

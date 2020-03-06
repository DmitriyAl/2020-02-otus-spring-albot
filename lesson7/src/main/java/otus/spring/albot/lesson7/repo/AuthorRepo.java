package otus.spring.albot.lesson7.repo;

import otus.spring.albot.lesson7.entity.Author;

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
public interface AuthorRepo {
    List<Author> findAllAuthors();

    List<Author> findAuthorByName(String name);

    int saveAuthor(String name);

    int deleteAuthor(long id);
}

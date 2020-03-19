package otus.spring.albot.lesson9.dao;

import otus.spring.albot.lesson9.entity.Genre;

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
public interface GenreDao {
    List<Genre> findAllGenres();

    List<Genre> findAllGenresByName(String template);

    Genre findById(long id);

    void addNewGenre(String name);

    void deleteGenre(long id);
}

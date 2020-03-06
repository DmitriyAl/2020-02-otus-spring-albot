package otus.spring.albot.lesson7.repo;

import otus.spring.albot.lesson7.entity.Genre;

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
public interface GenreRepo {
    List<Genre> findAllGenres();

    List<Genre> findAllGenresByName(String name);

    int saveGenre(String name);

    int deleteGenre(long id);
}

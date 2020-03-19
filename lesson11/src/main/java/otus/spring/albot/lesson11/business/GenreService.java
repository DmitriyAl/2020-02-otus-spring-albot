package otus.spring.albot.lesson11.business;

import otus.spring.albot.lesson11.entity.Genre;

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
public interface GenreService {
    List<Genre> findAllGenres();

    Genre findByName(String name);

    Genre addNewGenre(String name);

    boolean removeGenreById(long id);
}
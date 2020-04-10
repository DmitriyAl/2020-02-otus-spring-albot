package otus.spring.albot.lesson13.business;

import otus.spring.albot.lesson13.entity.Genre;
import otus.spring.albot.lesson13.exception.DependentBookException;
import otus.spring.albot.lesson13.exception.NoSuchGenreException;

import java.util.List;

public interface GenreService {
    List<Genre> findAllGenres();

    Genre findByName(String name);

    Genre addNewGenre(String name);

    void removeGenreById(String id) throws NoSuchGenreException, DependentBookException;
}

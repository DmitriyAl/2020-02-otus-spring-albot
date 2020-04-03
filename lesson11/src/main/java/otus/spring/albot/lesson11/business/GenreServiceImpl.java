package otus.spring.albot.lesson11.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson11.dao.GenreRepo;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Genre;
import otus.spring.albot.lesson11.exception.DependentBookException;
import otus.spring.albot.lesson11.exception.NoSuchGenreException;

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
@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreRepo genreRepo;

    @Override
    public List<Genre> findAllGenres() {
        return genreRepo.findAll();
    }

    @Override
    public Genre findByName(String name) {
        return genreRepo.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public Genre addNewGenre(String name) {
        return genreRepo.save(new Genre(name));
    }

    @Override
    @Transactional
    public void removeGenreById(long id) throws NoSuchGenreException, DependentBookException {
        Genre genre = genreRepo.findById(id).orElseThrow(() -> new NoSuchGenreException(id));
        checkDependentBook(genre);
        genreRepo.delete(genre);
    }

    private void checkDependentBook(Genre genre) throws DependentBookException {
        if (!genre.getBooks().isEmpty()) {
            StringBuilder sb =
                    new StringBuilder("The next list of books depends on this genre ('" + genre.getName() + "'):");
            for (Book book : genre.getBooks()) {
                sb.append("\n");
                sb.append(book.toString());
            }
            throw new DependentBookException(sb.toString());
        }
    }
}
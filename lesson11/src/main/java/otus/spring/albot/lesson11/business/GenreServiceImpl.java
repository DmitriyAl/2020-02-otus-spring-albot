package otus.spring.albot.lesson11.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson11.dao.GenreRepo;
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
    public boolean removeGenreById(long id) {
        try {
            genreRepo.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
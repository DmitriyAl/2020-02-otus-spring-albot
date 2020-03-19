package otus.spring.albot.lesson9.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import otus.spring.albot.lesson9.dao.GenreDao;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    @Override
    public boolean removeGenreById(long id) {
        try {
            genreDao.deleteGenre(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

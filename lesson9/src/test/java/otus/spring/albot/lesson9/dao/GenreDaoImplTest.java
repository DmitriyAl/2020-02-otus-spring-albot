package otus.spring.albot.lesson9.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

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
@DataJpaTest
@Import(GenreDaoImpl.class)
public class GenreDaoImplTest {
    @Autowired
    private GenreDao genreDao;

    @Test
    public void findAllGenres() throws Exception {
        assertThat(genreDao.findAllGenres()).hasSize(3).allMatch(genre -> genre.getName() != null)
                .allMatch(genre -> genre.getId() != 0);
    }

    @Test
    public void findAllGenresByName() throws Exception {
        assertThat(genreDao.findAllGenresByName("No")).hasSize(1).allMatch(genre -> genre.getName() != null)
                .allMatch(genre -> genre.getName().equals("Novel"));
    }
}
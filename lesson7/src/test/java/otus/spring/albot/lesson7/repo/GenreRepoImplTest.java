package otus.spring.albot.lesson7.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import otus.spring.albot.lesson7.entity.Genre;

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
@JdbcTest
@RunWith(SpringRunner.class)
@Import(GenreRepoImpl.class)
public class GenreRepoImplTest {
    @Autowired
    private GenreRepo genreRepo;

    @Test
    public void findAllGenres() throws Exception {
        assertThat(genreRepo.findAllGenres()).hasSize(3).allMatch(genre -> genre.getName() != null)
                .allMatch(genre -> genre.getId() != 0);
    }

    @Test
    public void findAllGenresByName() throws Exception {
        assertThat(genreRepo.findAllGenresByName("No")).hasSize(1).containsOnly(new Genre(2, "Novel"));
    }

}
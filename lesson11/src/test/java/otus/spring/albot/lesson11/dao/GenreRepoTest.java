package otus.spring.albot.lesson11.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import otus.spring.albot.lesson11.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GenreRepoTest {
    @Autowired
    private GenreRepo genreRepo;

    @Test
    void findByName() {
        Genre genre = genreRepo.findById(1L).orElseThrow(NullPointerException::new);
        String genreName = genre.getName();
        Genre genreByName = genreRepo.findByName(genreName).orElseThrow(NullPointerException::new);
        assertThat(genre).isEqualTo(genreByName);
    }
}
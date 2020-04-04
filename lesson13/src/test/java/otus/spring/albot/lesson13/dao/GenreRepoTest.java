package otus.spring.albot.lesson13.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import otus.spring.albot.lesson13.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan("otus.spring.albot.lesson13.listener")
class GenreRepoTest {
    @Autowired
    private GenreRepo genreRepo;

    @Test
    public void addAuthorAndRead() {
        assertThat(genreRepo.findAll()).isEmpty();
        Genre genre = genreRepo.save(new Genre("Test"));
        assertThat(genreRepo.findAll()).hasSize(1).allMatch(g -> g.getId().equals(genre.getId()));
    }

    @Test
    public void addAuthorAndRemove() {
        Genre genre = genreRepo.save(new Genre("Test"));
        assertThat(genreRepo.findAll()).hasSize(1).allMatch(g -> g.getId().equals(genre.getId()));
        genreRepo.delete(genre);
        assertThat(genreRepo.findAll()).isEmpty();
    }
}

package otus.spring.albot.lesson13.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import otus.spring.albot.lesson13.entity.Author;
import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.entity.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ComponentScan("otus.spring.albot.lesson13.listener")
class AuthorRepoTest {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private GenreRepo genreRepo;

    @Test
    public void addAuthorAndRead() {
        assertThat(authorRepo.findAll()).isEmpty();
        Author author = authorRepo.save(new Author("Test"));
        assertThat(authorRepo.findAll()).hasSize(1).allMatch(a -> a.getId().equals(author.getId()));
    }

    @Test
    public void addAuthorAndRemove() {
        Author author = authorRepo.save(new Author("Test"));
        assertThat(authorRepo.findAll()).hasSize(1).allMatch(a -> a.getId().equals(author.getId()));
        authorRepo.delete(author);
        assertThat(authorRepo.findAll()).isEmpty();
    }

    @Test
    public void removeAuthorWithBooks() {
        Author rawAuthor = authorRepo.save(new Author("Test"));
        Genre rawGenre = genreRepo.save(new Genre("Test"));
        final Book book = bookRepo.save(new Book("Test", rawAuthor, rawGenre));
        assertThat(authorRepo.findAll()).hasSize(1).allMatch(a -> a.getId().equals(rawAuthor.getId()));
        assertThat(genreRepo.findAll()).hasSize(1).allMatch(g -> g.getId().equals(rawGenre.getId()));
        assertThat(bookRepo.findAll()).hasSize(1).allMatch(b -> b.getId().equals(book.getId()));
        assertThat(authorRepo.findAll().get(0).getBooks()).hasSize(1).containsOnly(book);
        assertThat(genreRepo.findAll().get(0).getBooks()).hasSize(1).containsOnly(book);
        authorRepo.deleteById(rawAuthor.getId());
        assertThat(authorRepo.findAll()).isEmpty();
        assertThat(bookRepo.findAll()).isEmpty();
        assertThat(genreRepo.findAll()).hasSize(1).allMatch(b -> b.getId().equals(rawGenre.getId()));
        assertThat(genreRepo.findAll().get(0).getBooks()).isEmpty();
    }
}

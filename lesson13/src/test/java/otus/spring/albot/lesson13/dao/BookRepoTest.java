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
class BookRepoTest {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private GenreRepo genreRepo;

    @Test
    public void saveBookCheckRelations() {
        Author rawAuthor = authorRepo.save(new Author("Test"));
        Genre rawGenre = genreRepo.save(new Genre("Test"));
        final Book book = bookRepo.save(new Book("Test", rawAuthor, rawGenre));
        assertThat(authorRepo.findAll()).hasSize(1).allMatch(a -> a.getId().equals(rawAuthor.getId()));
        assertThat(bookRepo.findAll()).hasSize(1).allMatch(b -> b.getId().equals(book.getId()));
        assertThat(genreRepo.findAll()).hasSize(1).allMatch(g -> g.getId().equals(rawGenre.getId()));
        assertThat(book.getAuthor().getId()).isEqualTo(rawAuthor.getId());
        assertThat(book.getGenre().getId()).isEqualTo(rawGenre.getId());
        final Author author = authorRepo.findById(rawAuthor.getId()).orElseThrow(NullPointerException::new);
        assertThat(author.getBooks()).hasSize(1).contains(book);
        Genre genre = genreRepo.findById(rawGenre.getId()).orElseThrow(NullPointerException::new);
        assertThat(genre.getBooks()).hasSize(1).contains(book);
    }

    @Test
    public void deleteBookCheckRelations() {
        Author rawAuthor = authorRepo.save(new Author("Test"));
        Genre rawGenre = genreRepo.save(new Genre("Test"));
        final Book book = bookRepo.save(new Book("Test", rawAuthor, rawGenre));
        assertThat(authorRepo.findAll()).hasSize(1).allMatch(a -> a.getId().equals(rawAuthor.getId()));
        assertThat(bookRepo.findAll()).hasSize(1).allMatch(b -> b.getId().equals(book.getId()));
        assertThat(genreRepo.findAll()).hasSize(1).allMatch(g -> g.getId().equals(rawGenre.getId()));
        assertThat(book.getAuthor().getId()).isEqualTo(rawAuthor.getId());
        assertThat(book.getGenre().getId()).isEqualTo(rawGenre.getId());
        bookRepo.delete(book);
        assertThat(bookRepo.findAll()).isEmpty();
        assertThat(authorRepo.findAll()).hasSize(1).allMatch(a -> a.getId().equals(rawAuthor.getId()));
        assertThat(genreRepo.findAll()).hasSize(1).allMatch(g -> g.getId().equals(rawGenre.getId()));
        assertThat(authorRepo.findAll().get(0).getBooks()).isEmpty();
        assertThat(genreRepo.findAll().get(0).getBooks()).isEmpty();
    }
}

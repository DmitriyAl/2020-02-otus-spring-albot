package otus.spring.albot.lesson11.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import otus.spring.albot.lesson11.entity.Author;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Genre;
import otus.spring.albot.lesson11.entity.Note;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepoTest {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private GenreRepo genreRepo;

    @Test
    void findByName() {
    }

    @Test
    void removeBookWithCommentsToIt() {
        Optional<Book> optional = bookRepo.findById(1L);
        if (optional.isPresent()) {
            Book book = optional.get();
            List<Note> notes = book.getNotes();
            int notesForBook = notes.size();
            int allNotesAmount = noteRepo.findAll().size();
            bookRepo.delete(book);
            List<Note> updatedNotes = noteRepo.findAll();
            int notesAmountAfterDeletion = updatedNotes.size();
            assertThat(notesAmountAfterDeletion).isEqualTo(allNotesAmount - notesForBook);
            assertThat(updatedNotes).noneMatch(n -> n.getBook().getId() == 1L);
        }
    }

    @Test
    void addBookWithNewAuthorAndNewGenre() {
        Author author = new Author("Test Author");
        Genre genre = new Genre("Test Genre");
        Book book = new Book("Test Book", author, genre);
        int authorAmount = authorRepo.findAll().size();
        int genreAmount = genreRepo.findAll().size();
        bookRepo.save(book);
        List<Author> authors = authorRepo.findAll();
        List<Genre> genres = genreRepo.findAll();
        assertThat(authorAmount).isEqualTo(authors.size() - 1);
        assertThat(genreAmount).isEqualTo(genres.size() - 1);
        assertThat(authors).anyMatch(a -> a.getName().equals(author.getName()));
        assertThat(genres).anyMatch(g -> g.getName().equals(genre.getName()));
    }
}
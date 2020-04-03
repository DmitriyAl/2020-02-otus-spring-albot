package otus.spring.albot.lesson11.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import otus.spring.albot.lesson11.entity.Author;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Genre;
import otus.spring.albot.lesson11.entity.Note;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
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
        Book book = bookRepo.findById(1L).orElseThrow(NullPointerException::new);
        String bookName = book.getName();
        Book bookByName = bookRepo.findByName(bookName).orElseThrow(NullPointerException::new);
        assertThat(book).isEqualTo(bookByName);
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
        bookRepo.saveAndFlush(book);
        List<Author> authors = authorRepo.findAll();
        List<Genre> genres = genreRepo.findAll();
        assertThat(authorAmount).isEqualTo(authors.size() - 1);
        assertThat(genreAmount).isEqualTo(genres.size() - 1);
        assertThat(authors).anyMatch(a -> a.getName().equals(author.getName()));
        assertThat(genres).anyMatch(g -> g.getName().equals(genre.getName()));
    }

    @Test
    void addBookWithExistedAuthorAndExistedGenre() {
        Author author = authorRepo.findById(1L).orElseThrow(NullPointerException::new);
        Genre genre = genreRepo.findById(1L).orElseThrow(NullPointerException::new);
        String newAuthorName = "New name";
        String newGenreName = "New genre";
        author.setName(newAuthorName);
        genre.setName(newGenreName);
        Book book = new Book("Test Book", author, genre);
        bookRepo.saveAndFlush(book);
        Author updatedAuthor = authorRepo.findById(1L).orElseThrow(NullPointerException::new);
        Genre updatedGenre = genreRepo.findById(1L).orElseThrow(NullPointerException::new);
        assertThat(updatedAuthor.getName()).isEqualTo(newAuthorName);
        assertThat(updatedGenre.getName()).isEqualTo(newGenreName);
    }

    @Test
    void modifyBookWithAuthorAndGenre() {
        Book book = bookRepo.findById(1L).orElseThrow(NullPointerException::new);
        String newBookName = "New book";
        book.setName(newBookName);
        String newAuthorName = "New name";
        String newGenreName = "New genre";
        book.getAuthor().setName(newAuthorName);
        book.getGenre().setName(newGenreName);
        Book savedBook = bookRepo.saveAndFlush(book);
        assertThat(savedBook.getName()).isEqualTo(newBookName);
        assertThat(savedBook.getAuthor().getName()).isEqualTo(newAuthorName);
        assertThat(savedBook.getGenre().getName()).isEqualTo(newGenreName);
    }
}
package otus.spring.albot.lesson13.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import otus.spring.albot.lesson13.entity.Author;
import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.entity.Genre;
import otus.spring.albot.lesson13.entity.Note;

import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
@ComponentScan("otus.spring.albot.lesson13.listener")
class NoteRepoTest {
    @Autowired
    private NoteRepo noteRepo;
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private GenreRepo genreRepo;

    @Test
    public void addNoteToBook() {
        Author rawAuthor = authorRepo.save(new Author("Test"));
        Genre rawGenre = genreRepo.save(new Genre("Test"));
        Book rawBook = bookRepo.save(new Book("Test", rawAuthor, rawGenre));
        Note note = noteRepo.save(new Note("Test", rawBook));
        assertThat(noteRepo.findAll()).hasSize(1).allMatch(n -> n.getId().equals(note.getId()));
        assertThat(noteRepo.findAll().get(0).getBook().getId()).isEqualTo(rawBook.getId());
        assertThat(bookRepo.findAll()).hasSize(1).allMatch(b -> b.getId().equals(rawBook.getId()));
        assertThat(bookRepo.findAll().get(0).getNotes()).hasSize(1).allMatch(n->n.getId().equals(note.getId()));
    }

    @Test
    public void deleteNote() {
        Author rawAuthor = authorRepo.save(new Author("Test"));
        Genre rawGenre = genreRepo.save(new Genre("Test"));
        Book rawBook = bookRepo.save(new Book("Test", rawAuthor, rawGenre));
        Note note = noteRepo.save(new Note("Test", rawBook));
        noteRepo.delete(note);
        assertThat(noteRepo.findAll()).isEmpty();
        assertThat(bookRepo.findAll()).hasSize(1).allMatch(b-> b.getId().equals(rawBook.getId()));
        assertThat(bookRepo.findAll().get(0).getNotes()).isEmpty();
    }

    @Test
    public void deleteBookWithNotes() {
        Author rawAuthor = authorRepo.save(new Author("Test"));
        Genre rawGenre = genreRepo.save(new Genre("Test"));
        Book rawBook = bookRepo.save(new Book("Test", rawAuthor, rawGenre));
        noteRepo.save(new Note("Test", rawBook));
        bookRepo.deleteById(rawBook.getId());
        assertThat(noteRepo.findAll()).isEmpty();
    }
}

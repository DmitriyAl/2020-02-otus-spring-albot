package otus.spring.albot.lesson13.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson13.dao.AuthorRepo;
import otus.spring.albot.lesson13.dao.BookRepo;
import otus.spring.albot.lesson13.dao.GenreRepo;
import otus.spring.albot.lesson13.dao.NoteRepo;
import otus.spring.albot.lesson13.entity.Author;
import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.entity.Genre;
import otus.spring.albot.lesson13.entity.Note;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class BookRepoListener extends AbstractMongoEventListener<Book> {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;
    private GenreRepo genreRepo;
    private NoteRepo noteRepo;

    @Override
    public void onAfterSave(AfterSaveEvent<Book> event) {
        super.onAfterSave(event);
        final Book source = event.getSource();
        authorRepo.findById(source.getAuthor().getId()).ifPresent(a -> addBookToAuthor(a, source));
        genreRepo.findById(source.getGenre().getId()).ifPresent(g -> addBookToGenre(g, source));
    }

    private void addBookToAuthor(Author author, Book book) {
        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<>());
        }
        if (!author.getBooks().contains(book)) {
            author.getBooks().add(book);
        }
        authorRepo.save(author);
    }

    private void addBookToGenre(Genre genre, Book book) {
        if (genre.getBooks() == null) {
            genre.setBooks(new ArrayList<>());
        }
        if (!genre.getBooks().contains(book)) {
            genre.getBooks().add(book);
        }
        genreRepo.save(genre);
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        List<Note> notes = new ArrayList<>();
        bookRepo.findById(id).ifPresent(b -> {
            if (b.getNotes() != null) {
                notes.addAll(b.getNotes());
            }
        });
        noteRepo.deleteAll(notes);
    }
}

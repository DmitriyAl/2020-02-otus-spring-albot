package otus.spring.albot.lesson25.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson25.dao.AuthorRepo;
import otus.spring.albot.lesson25.dao.BookRepo;
import otus.spring.albot.lesson25.dao.GenreRepo;
import otus.spring.albot.lesson25.dao.NoteRepo;
import otus.spring.albot.lesson25.document.AuthorDoc;
import otus.spring.albot.lesson25.document.BookDoc;
import otus.spring.albot.lesson25.document.GenreDoc;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class BookRepoListener extends AbstractMongoEventListener<BookDoc> {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;
    private GenreRepo genreRepo;
    private NoteRepo noteRepo;

    @Override
    public void onAfterSave(AfterSaveEvent<BookDoc> event) {
        super.onAfterSave(event);
        final BookDoc source = event.getSource();
        authorRepo.findById(source.getAuthor().getId()).ifPresent(a -> addBookToAuthor(a, source));
        genreRepo.findById(source.getGenre().getId()).ifPresent(g -> addBookToGenre(g, source));
    }

    private void addBookToAuthor(AuthorDoc author, BookDoc book) {
        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<>());
        }
        if (!author.getBooks().contains(book)) {
            author.getBooks().add(book);
        }
        authorRepo.save(author);
    }

    private void addBookToGenre(GenreDoc genre, BookDoc book) {
        if (genre.getBooks() == null) {
            genre.setBooks(new ArrayList<>());
        }
        if (!genre.getBooks().contains(book)) {
            genre.getBooks().add(book);
        }
        genreRepo.save(genre);
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<BookDoc> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        bookRepo.findById(id).ifPresent(book -> {
            if (book.getNotes() != null) {
                noteRepo.deleteAll(book.getNotes());
            }
            genreRepo.findById(book.getGenre().getId()).ifPresent(genre -> {
                if (genre.getBooks() != null) {
                    genre.getBooks().remove(book);
                    genreRepo.save(genre);
                }
            });
            authorRepo.findById(book.getAuthor().getId()).ifPresent(author -> {
                if (author.getBooks() != null) {
                    author.getBooks().remove(book);
                    authorRepo.save(author);
                }
            });
        });
    }
}

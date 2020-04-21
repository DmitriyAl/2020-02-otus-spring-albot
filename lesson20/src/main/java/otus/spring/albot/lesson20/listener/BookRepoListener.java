package otus.spring.albot.lesson20.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson20.dao.AuthorRepo;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.GenreRepo;
import otus.spring.albot.lesson20.dao.NoteRepo;
import otus.spring.albot.lesson20.entity.Author;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Genre;
import reactor.core.publisher.Mono;

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
        authorRepo.findById(source.getAuthor().getId()).flatMap(a -> addBookToAuthor(a, source)).subscribe();
        genreRepo.findById(source.getGenre().getId()).flatMap(g -> addBookToGenre(g, source)).subscribe();
    }

    private Mono<Author> addBookToAuthor(Author author, Book book) {
        if (author.getBooks() == null) {
            author.setBooks(new ArrayList<>());
        }
        if (!author.getBooks().contains(book)) {
            author.getBooks().add(book);
        }
        return authorRepo.save(author);
    }

    private Mono<Genre> addBookToGenre(Genre genre, Book book) {
        if (genre.getBooks() == null) {
            genre.setBooks(new ArrayList<>());
        }
        if (!genre.getBooks().contains(book)) {
            genre.getBooks().add(book);
        }
        return genreRepo.save(genre);
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Book> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        Mono<Book> bookMono = bookRepo.findById(id);
        bookMono.flatMap(this::deleteNotes).subscribe();
        bookMono.flatMap(this::updateAuthors).subscribe();
        bookMono.flatMap(this::updateGenres).subscribe();
    }

    private Mono<Void> deleteNotes(Book book) {
        if (book.getNotes() != null) {
            return noteRepo.deleteAll(book.getNotes());
        }
        return Mono.empty();
    }

    private Mono<Genre> updateGenres(Book book) {
        List<Book> books = book.getGenre().getBooks();
        if (books != null) {
            books.remove(book);
            return genreRepo.save(book.getGenre());
        }
        return Mono.empty();
    }

    private Mono<Author> updateAuthors(Book book) {
        List<Book> books = book.getAuthor().getBooks();
        if (books != null) {
            books.remove(book);
            return authorRepo.save(book.getAuthor());
        }
        return Mono.empty();
    }
}

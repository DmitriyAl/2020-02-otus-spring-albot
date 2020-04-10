package otus.spring.albot.lesson13.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson13.dao.AuthorRepo;
import otus.spring.albot.lesson13.dao.BookRepo;
import otus.spring.albot.lesson13.dao.GenreRepo;
import otus.spring.albot.lesson13.entity.Author;
import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.entity.Genre;
import otus.spring.albot.lesson13.exception.NoSuchAuthorException;
import otus.spring.albot.lesson13.exception.NoSuchBookException;
import otus.spring.albot.lesson13.exception.NoSuchGenreException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book findByName(String name) {
        return bookRepo.findByName(name).orElse(null);
    }

    @Override
    public Book findById(String id) {
        return bookRepo.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public List<Book> findAllBooksByAuthorId(String authorId) {
        Author author = authorRepo.findById(authorId).orElse(null);
        if (author != null) {
            return new ArrayList<>(author.getBooks());
        }
        return Collections.emptyList();
    }

    @Override
    @Transactional
    public Book addNewBook(String name, String authorId, String genreId) throws NoSuchAuthorException, NoSuchGenreException {
        Author author = authorRepo.findById(authorId)
                .orElseThrow(() -> new NoSuchAuthorException("The author with id '" + authorId + "' doesn't exist"));
        Genre genre = genreRepo.findById(genreId)
                .orElseThrow(() -> new NoSuchGenreException("The genre with id '" + genreId + "' doesn't exist"));
        return bookRepo.save(new Book(name, author, genre));
    }

    @Override
    @Transactional
    public void removeBookById(String id) throws NoSuchBookException {
        Book book = bookRepo.findById(id)
                .orElseThrow(() -> new NoSuchBookException("The book with id: " + id + " does not exist"));
        bookRepo.delete(book);
    }
}

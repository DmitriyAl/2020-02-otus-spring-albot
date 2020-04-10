package otus.spring.albot.lesson13.business;

import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.exception.NoSuchAuthorException;
import otus.spring.albot.lesson13.exception.NoSuchBookException;
import otus.spring.albot.lesson13.exception.NoSuchGenreException;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();

    Book findByName(String name);

    Book findById(String id);

    List<Book> findAllBooksByAuthorId(String id);

    Book addNewBook(String name, String authorId, String genreId) throws NoSuchAuthorException, NoSuchGenreException;

    void removeBookById(String id) throws NoSuchBookException;
}

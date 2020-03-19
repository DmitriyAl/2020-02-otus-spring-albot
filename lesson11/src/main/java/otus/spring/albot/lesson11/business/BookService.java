package otus.spring.albot.lesson11.business;

import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.exception.NoSuchAuthorException;
import otus.spring.albot.lesson11.exception.NoSuchGenreException;

import java.util.List;

/**
 * <pre>
 * $Id: $
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * </pre>
 *
 * @author Dmitrii Albot
 */
public interface BookService {
    List<Book> findAllBooks();

    Book findByName(String name);

    Book findById(long id);

    List<Book> findAllBooksByAuthorId(long id);

    Book addNewBook(String name, long authorId, long genreId) throws NoSuchAuthorException, NoSuchGenreException;

    void removeBookById(long id);
}
package otus.spring.albot.lesson9.business;

import otus.spring.albot.lesson9.entity.Book;

import java.util.List;

public interface BookService {
    void addNewBook(String name, long authorId, long genreId);

    void removeBookById(long id);

    List<Book> findAllBooksByAuthorId(long id);
}

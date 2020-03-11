package otus.spring.albot.lesson9.business;

public interface BookService {
    void addNewBook(String name, long authorId, long genreId);

    void removeBookById(long id);
}

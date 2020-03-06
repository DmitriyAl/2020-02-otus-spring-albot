package otus.spring.albot.lesson7.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.albot.lesson7.entity.Author;
import otus.spring.albot.lesson7.entity.Book;
import otus.spring.albot.lesson7.entity.Genre;
import otus.spring.albot.lesson7.repo.AuthorRepo;
import otus.spring.albot.lesson7.repo.BookRepo;
import otus.spring.albot.lesson7.repo.GenreRepo;

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
@ShellComponent
@AllArgsConstructor
public class ShellController {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;
    private GenreRepo genreRepo;

    @ShellMethod(value = "Get all authors", key = {"get-all-authors", "gaa"})
    public List<Author> getAllAuthors() {
        return authorRepo.findAllAuthors();
    }

    @ShellMethod(value = "Get authors matching template", key = {"get-authors-by-template", "gat"})
    public List<Author> getAuthorsByTemplate(@ShellOption(defaultValue = "") String template) {
        return authorRepo.findAuthorByName(template);
    }

    @ShellMethod(value = "Add a new author", key = {"add-new-author", "ana"})
    public String addNewAuthor(@ShellOption String name) {
        authorRepo.saveAuthor(name);
        return "The new author was added";
    }

    @ShellMethod(value = "Delete the author with id", key = {"delete-author", "da"})
    public String deleteAuthor(@ShellOption long id) {
        int count = authorRepo.deleteAuthor(id);
        if (count == 0) {
            return "No author with such id was found";
        }
        return "The author was removed";
    }

    @ShellMethod(value = "Get all books", key = {"get-all-books", "gab"})
    public List<Book> getAllBooks() {
        return bookRepo.findAllBooks();
    }

    @ShellMethod(value = "Get books matching template", key = {"get-books-by-template", "gbt"})
    public List<Book> getBooksByTemplate(@ShellOption(defaultValue = "") String template) {
        return bookRepo.findBookByName(template);
    }

    @ShellMethod(value = "Add a new book", key = {"add-new-book", "anb"})
    public String addNewBook(@ShellOption String name) {
        bookRepo.saveBook(name);
        return "The new book was added";
    }

    @ShellMethod(value = "Delete the book with id", key = {"delete-book", "db"})
    public String deleteBook(@ShellOption long id) {
        int count = bookRepo.deleteBook(id);
        if (count == 0) {
            return "No book with such id was found";
        }
        return "The book was removed";
    }

    @ShellMethod(value = "Get all genres", key = {"get-all-genres", "gag"})
    public List<Genre> getAllGenres() {
        return genreRepo.findAllGenres();
    }

    @ShellMethod(value = "Get gentes matching template", key = {"get-genres-by-template", "ggt"})
    public List<Genre> getGenresByTemplate(@ShellOption(defaultValue = "") String template) {
        return genreRepo.findAllGenresByName(template);
    }

    @ShellMethod(value = "Add a new genre", key = {"add-new-genre", "ang"})
    public String addNewGenre(@ShellOption String name) {
        genreRepo.saveGenre(name);
        return "The new genre was added";
    }

    @ShellMethod(value = "Delete the genre with id", key = {"delete-genre", "dg"})
    public String deleteGenre(@ShellOption long id) {
        int count = genreRepo.deleteGenre(id);
        if (count == 0) {
            return "No genre with such id was found";
        }
        return "The genre was removed";
    }
}

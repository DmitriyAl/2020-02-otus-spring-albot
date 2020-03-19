package otus.spring.albot.lesson9.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.albot.lesson9.business.*;
import otus.spring.albot.lesson9.dao.AuthorDao;
import otus.spring.albot.lesson9.dao.BookDao;
import otus.spring.albot.lesson9.dao.GenreDao;
import otus.spring.albot.lesson9.entity.Author;
import otus.spring.albot.lesson9.entity.Book;
import otus.spring.albot.lesson9.entity.Comment;
import otus.spring.albot.lesson9.entity.Genre;
import otus.spring.albot.lesson9.model.BookComments;

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
    private BookCommentPreparer bookCommentPreparer;
    private CommentService commentService;
    private BookService bookService;
    private GenreService genreService;
    private AuthorService authorService;
    private AuthorDao authorDao;
    private BookDao bookDao;
    private GenreDao genreDao;

    @ShellMethod(value = "Get all authors.", key = {"get-all-authors", "gaa"})
    public List<Author> getAllAuthors() {
        return authorDao.findAllAuthors();
    }

    @ShellMethod(value = "Get author by template. Parameters: 'template'.", key = {"get-authors-by-template", "gat"})
    public List<Author> getAuthorsByTemplate(@ShellOption String template) {
        return authorDao.findAuthorByTemplate(template);
    }

    @ShellMethod(value = "Add a new author. Parameters: 'name'.", key = {"add-new-author", "ana"})
    public String addNewAuthor(@ShellOption String name) {
        authorDao.addNewAuthor(name);
        return "The new author was added";
    }

    @ShellMethod(value = "Remove the author. Parameters: 'id'.", key = {"delete-author", "da"})
    public String deleteAuthor(@ShellOption long id) {
        boolean status = authorService.removeAuthorById(id);
        return status ? "The author was removed" : "The author was not removed. Probably you have the books depended from this author";
    }

    @ShellMethod(value = "Change author name. Parameters: 'id' 'name'.", key = {"change-author-name", "can"})
    public String changeAuthorName(@ShellOption long id, String newName) {
        Author author = authorDao.changeAuthorName(id, newName);
        return "Author was changed, new author is " + author.toString();
    }

    @ShellMethod(value = "Get all books.", key = {"get-all-books", "gab"})
    public List<Book> getAllBooks() {
        return bookDao.findAllBooks();
    }

    @ShellMethod(value = "Get all books by author. Parameter 'authorId'", key = {"get-all-books-by-author", "gabba"})
    public List<Book> getAllBooksByAuthor(@ShellOption long authorId) {
        return bookService.findAllBooksByAuthorId(authorId);
    }

    @ShellMethod(value = "Get book by template. Parameters: 'template'.", key = {"get-books-by-template", "gbt"})
    public List<Book> getBooksByTemplate(@ShellOption String template) {
        return bookDao.findBookByName(template);
    }

    @ShellMethod(value = "Get book by id. Parameters: 'id'.", key = {"get-book-by-id", "gbbi"})
    public Book getBookById(@ShellOption long id) {
        return bookDao.findBookById(id);
    }

    @ShellMethod(value = "Add a new book. Parameters: 'name' 'author id' 'genre id'.", key = {"add-new-book", "anb"})
    public String addNewBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        bookService.addNewBook(name, authorId, genreId);
        return "The new book was added";
    }

    @ShellMethod(value = "Remove the book. Parameters: 'id'.", key = {"delete-book", "db"})
    public String deleteBook(long id) {
        bookService.removeBookById(id);
        return "The book was removed";
    }

    @ShellMethod(value = "Get all genres.", key = {"get-all-genres", "gag"})
    public List<Genre> getAllGenres() {
        return genreDao.findAllGenres();
    }

    @ShellMethod(value = "Get genre by template. Parameters: 'template'.", key = {"get-genres-by-template", "ggt"})
    public List<Genre> getGenresByTemplate(@ShellOption(defaultValue = "") String template) {
        return genreDao.findAllGenresByName(template);
    }

    @ShellMethod(value = "Add a new genre. Parameters: 'name'.", key = {"add-new-genre", "ang"})
    public String addNewGenre(@ShellOption String name) {
        genreDao.addNewGenre(name);
        return "The new genre was added";
    }

    @ShellMethod(value = "Remove the genre. Parameters: 'id'.", key = {"delete-genre", "dg"})
    public String deleteGenre(@ShellOption long id) {
        boolean status = genreService.removeGenreById(id);
        return status ? "The genre was removed" : "The genre was not removed. Probably you have the books depended from this genre";
    }

    @ShellMethod(value = "Get all comments for the book by template. Parameters: 'template'.", key = {"get-all-comments-for-the-book-by-template", "gacbbt"})
    public List<BookComments> getAllCommentsForBookByTemplate(@ShellOption(defaultValue = "") String template) {
        return bookCommentPreparer.extractAllCommentsForBookByTemplate(template);
    }

    @ShellMethod(value = "Get all comments for the book by id. Parameters: 'id'.", key = {"get-all-comments-for-the-book-by-id", "gacbbi"})
    public BookComments getAllCommentsForBookById(@ShellOption long id) {
        return bookCommentPreparer.extractAllCommentsForBookById(id);
    }

    @ShellMethod(value = "Add comment to the book with id. Parameters: 'id' 'name'.", key = {"book-add-comment", "bac"})
    public Comment addCommentToBook(@ShellOption long id, @ShellOption String comment) {
        return commentService.addCommentToBook(id, comment);
    }

    @ShellMethod(value = "Remove comment for the book by comment id. Parameters: 'id'.", key = {"remove-comment-id", "rci"})
    public String removeCommentFromBookById(@ShellOption long id) {
        commentService.removeComment(id);
        return "The comment was removed";
    }
}

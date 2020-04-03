package otus.spring.albot.lesson11.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.albot.lesson11.business.*;
import otus.spring.albot.lesson11.entity.Author;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Genre;
import otus.spring.albot.lesson11.entity.Note;
import otus.spring.albot.lesson11.exception.*;
import otus.spring.albot.lesson11.model.BookNotes;

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
    private AuthorService authorService;
    private BookService bookService;
    private GenreService genreService;
    private BookCommentPreparer bookCommentPreparer;
    private NoteService noteService;

    @ShellMethod(value = "Get all authors", key = {"get-all-authors", "gaa"})
    public List<Author> getAllAuthors() {
        return authorService.findAllAuthors();
    }

    @ShellMethod(value = "Get author with name. Parameters: 'authorName'", key = {"get-authors-by-name", "gan"})
    public Author getAuthorsByName(@ShellOption(defaultValue = "") String authorName) {
        return authorService.findByName(authorName);
    }

    @ShellMethod(value = "Add a new author. Parameters: 'name'.", key = {"add-new-author", "ana"})
    public String addNewAuthor(@ShellOption String name) {
        Author author = authorService.saveNewAuthor(name);
        return "The new author was added: " + author.toString();
    }

    @ShellMethod(value = "Remove the author. Parameters: 'id'.", key = {"delete-author", "da"})
    public String deleteAuthor(@ShellOption long id) {
        boolean status = authorService.removeAuthorById(id);
        return status ? "The author was removed" : "The author was not removed. Probably you have the books depended from this author";
    }

    @ShellMethod(value = "Change author name. Parameters: 'id' 'name'.", key = {"change-author-name", "can"})
    public String changeAuthorName(@ShellOption long id, String newName) {
        Author author = authorService.changeAuthorName(id, newName);
        if (author == null) {
            return "No such author";
        }
        return "Author was changed, new author is " + author.toString();
    }

    @ShellMethod(value = "Get all books", key = {"get-all-books", "gab"})
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @ShellMethod(value = "Get all books by author. Parameter 'authorId'", key = {"get-all-books-by-author", "gabba"})
    public List<Book> getAllBooksByAuthor(@ShellOption long authorId) {
        return bookService.findAllBooksByAuthorId(authorId);
    }

    @ShellMethod(value = "Get book with name. Parameters: 'bookName'.", key = {"get-books-by-name", "gbn"})
    public Book getBooksByName(@ShellOption(defaultValue = "") String bookName) {
        return bookService.findByName(bookName);
    }

    @ShellMethod(value = "Get book by id. Parameters: 'id'.", key = {"get-book-by-id", "gbbi"})
    public Book getBookById(@ShellOption long id) {
        return bookService.findById(id);
    }

    @ShellMethod(value = "Add a new book. Parameters: 'name' 'author id' 'genre id'.", key = {"add-new-book", "anb"})
    public String addNewBook(@ShellOption String name, @ShellOption long authorId, @ShellOption long genreId) {
        try {
            bookService.addNewBook(name, authorId, genreId);
        } catch (NoSuchAuthorException | NoSuchGenreException e) {
            return e.getMessage();
        }
        return "The new book was added";
    }

    @ShellMethod(value = "Remove the book. Parameters: 'id'.", key = {"delete-book", "db"})
    public String deleteBook(long id) {
        try {
            bookService.removeBookById(id);
        } catch (NoSuchBookException e) {
            return e.getMessage();
        }
        return "The book was removed";
    }

    @ShellMethod(value = "Get all genres", key = {"get-all-genres", "gag"})
    public List<Genre> getAllGenres() {
        return genreService.findAllGenres();
    }

    @ShellMethod(value = "Get genre with name. Parameters: 'genreName'.", key = {"get-genres-by-name", "ggn"})
    public Genre getGenresByName(@ShellOption(defaultValue = "") String genreName) {
        return genreService.findByName(genreName);
    }

    @ShellMethod(value = "Add a new genre. Parameters: 'name'.", key = {"add-new-genre", "ang"})
    public String addNewGenre(@ShellOption String name) {
        Genre genre = genreService.addNewGenre(name);
        return "The new genre was added: " + genre.toString();
    }

    @ShellMethod(value = "Remove the genre. Parameters: 'id'.", key = {"delete-genre", "dg"})
    public String deleteGenre(@ShellOption long id) {
        try {
            genreService.removeGenreById(id);
        } catch (NoSuchGenreException| DependentBookException e) {
            return e.getMessage();
        }
        return "The genre was removed";
    }

    @ShellMethod(value = "Get all comments for the book by name. Parameters: 'bookName'.", key = {
            "get-all-comments-for-the-book-by-name", "gacbbn"})
    public BookNotes getAllCommentsForBookByName(@ShellOption(defaultValue = "") String bookName) {
        return bookCommentPreparer.extractAllNotesForBookByName(bookName);
    }

    @ShellMethod(value = "Get all comments for the book by id. Parameters: 'id'.", key = {"get-all-comments-for-the-book-by-id", "gacbbi"})
    public BookNotes getAllCommentsForBookById(@ShellOption long id) {
        return bookCommentPreparer.extractAllNotesForBookById(id);
    }

    @ShellMethod(value = "Add comment to the book with id. Parameters: 'id' 'comment'.", key = {"bac", "book-add-comment"})
    public Note addCommentToBook(@ShellOption long id, @ShellOption String comment) {
        return noteService.addNoteToBook(id, comment);
    }

    @ShellMethod(value = "Remove comment for the book by comment id. Parameters: 'id'.", key = {"remove-comment-id", "rci"})
    public String removeCommentFromBookById(@ShellOption long id) {
        try {
            noteService.removeNote(id);
        } catch (NoSuchNoteException e) {
            return e.getMessage();
        }
        return "The comment was removed";
    }
}
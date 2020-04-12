package otus.spring.albot.lesson20.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import otus.spring.albot.lesson20.business.BookService;
import otus.spring.albot.lesson20.entity.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class BookController {
    private BookService bookService;

    @GetMapping(value = "books")
    public Flux<Book> getAllBooks(@RequestParam(value = "authorId", required = false) String authorId) {
        if (authorId != null) {
            return bookService.findAllBooksByAuthorId(authorId);
        }
        return bookService.findAllBooks();
    }

    @GetMapping(value = "books/{id}")
    public Mono<Book> getBookById(@PathVariable("id") String id) {
        return bookService.findById(id);
    }

    @PostMapping(value = "books")
    public Mono<Book> saveBook(@RequestParam("bookName") String bookName,
                               @RequestParam("authorId") String authorId,
                               @RequestParam("genreId") String genreId) {
        return bookService.addNewBook(bookName, authorId, genreId);
    }

    @DeleteMapping(value = "books")
    public Mono<Void> deleteBook(@RequestParam("id") String id) {
        return bookService.removeBookById(id);
    }
}

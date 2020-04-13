package otus.spring.albot.lesson20.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.albot.lesson20.dao.AuthorRepo;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.GenreRepo;
import otus.spring.albot.lesson20.entity.Author;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Genre;
import otus.spring.albot.lesson20.exception.NoSuchAuthorException;
import otus.spring.albot.lesson20.exception.NoSuchBookException;
import otus.spring.albot.lesson20.exception.NoSuchGenreException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class BookServiceImplTest {
    @Autowired
    private BookService bookService;
    @MockBean
    private BookRepo bookRepo;
    @MockBean
    private AuthorRepo authorRepo;
    @MockBean
    private GenreRepo genreRepo;

    @Test
    void findAllBooksByAuthorId() {
        Author pushkin = new Author("Pushkin");
        pushkin.setId("id");
        Genre novel = new Genre("Novel");
        Book book = new Book("Book", pushkin, novel);
        book.setId("id");
        given(authorRepo.findById(pushkin.getId())).willReturn(Mono.just(pushkin));
        given(bookRepo.findAllByAuthor(pushkin)).willReturn(Flux.just(book));
        Flux<Book> allBooksByAuthorId = bookService.findAllBooksByAuthorId(pushkin.getId());
        StepVerifier.create(allBooksByAuthorId).expectNext(book).expectComplete().verify();
    }

    @Test
    void findAllBooksByWrongAuthorId() {
        Author pushkin = new Author("Pushkin");
        pushkin.setId("id");
        Genre novel = new Genre("Novel");
        Book book = new Book("Book", pushkin, novel);
        given(authorRepo.findById(pushkin.getId())).willReturn(Mono.empty());
        Flux<Book> allBooksByAuthorId = bookService.findAllBooksByAuthorId(pushkin.getId());
        StepVerifier.create(allBooksByAuthorId).expectError(NoSuchAuthorException.class).verify();
    }

    @Test
    void addNewBook() {
        Author pushkin = new Author("Pushkin");
        pushkin.setId("id");
        Genre novel = new Genre("Novel");
        novel.setId("id");
        Book book = new Book("Book", pushkin, novel);
        given(bookRepo.save(book)).willReturn(Mono.just(book));
        given(authorRepo.findById(pushkin.getId())).willReturn(Mono.just(pushkin));
        given(genreRepo.findById(novel.getId())).willReturn(Mono.just(novel));
        Mono<Book> bookMono = bookService.addNewBook(book);
        StepVerifier.create(bookMono).expectNext(book).expectComplete().verify();
    }

    @Test
    void addNewBookWrongAuthor() {
        Author pushkin = new Author("Pushkin");
        pushkin.setId("id");
        Genre novel = new Genre("Novel");
        novel.setId("id");
        Book book = new Book("Book", pushkin, novel);
        given(bookRepo.save(book)).willReturn(Mono.just(book));
        given(authorRepo.findById(pushkin.getId())).willReturn(Mono.empty());
        given(genreRepo.findById(novel.getId())).willReturn(Mono.just(novel));
        Mono<Book> bookMono = bookService.addNewBook(book);
        StepVerifier.create(bookMono).expectError(NoSuchAuthorException.class).verify();
    }

    @Test
    void addNewBookWrongGenre() {
        Author pushkin = new Author("Pushkin");
        pushkin.setId("id");
        Genre novel = new Genre("Novel");
        novel.setId("id");
        Book book = new Book("Book", pushkin, novel);
        given(bookRepo.save(book)).willReturn(Mono.just(book));
        given(authorRepo.findById(pushkin.getId())).willReturn(Mono.just(pushkin));
        given(genreRepo.findById(novel.getId())).willReturn(Mono.empty());
        Mono<Book> bookMono = bookService.addNewBook(book);
        StepVerifier.create(bookMono).expectError(NoSuchGenreException.class).verify();
    }
}

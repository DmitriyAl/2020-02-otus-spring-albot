package otus.spring.albot.lesson20.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import otus.spring.albot.lesson20.business.BookService;
import otus.spring.albot.lesson20.entity.Author;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Genre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
class BookControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private BookService bookService;

    @Test
    void getAllBooks() {
        Author pushkin = new Author("Pushkin");
        Genre novel = new Genre("Novel");
        Book book1 = new Book("Book1", pushkin, novel);
        Book book2 = new Book("Book2", pushkin, novel);
        given(bookService.findAllBooks()).willReturn(Flux.just(book1, book2));
        webTestClient.get().uri("/books").exchange()
                .expectStatus().isOk().expectBodyList(Book.class).hasSize(2).contains(book1, book2);
    }

    @Test
    void getBookById() {
        Author pushkin = new Author("Pushkin");
        Genre novel = new Genre("Novel");
        Book book = new Book("Book", pushkin, novel);
        book.setId("id");
        given(bookService.findById(book.getId())).willReturn(Mono.just(book));
        webTestClient.get().uri("/books/{id}", book.getId()).exchange()
                .expectStatus().isOk().expectBody(Book.class).isEqualTo(book);
    }

    @Test
    void saveBook() {
        Author pushkin = new Author("Pushkin");
        Genre novel = new Genre("Novel");
        Book before = new Book("Book", pushkin, novel);
        Book after = new Book("Book", pushkin, novel);
        after.setId("id");
        given(bookService.addNewBook(before)).willReturn(Mono.just(after));
        webTestClient.post().uri("/books").bodyValue(before)
                .exchange().expectStatus().isOk().expectBody(Book.class).isEqualTo(after);
    }

    @Test
    void deleteBook() {
        Author pushkin = new Author("Pushkin");
        Genre novel = new Genre("Novel");
        Book book = new Book("Book", pushkin, novel);
        book.setId("id");
        given(bookService.removeBookById(book.getId())).willReturn(Mono.empty());
        webTestClient.delete().uri(b -> b.path("/books").queryParam("id", book.getId()).build())
                .exchange().expectStatus().isOk().expectBody(Void.class);
    }
}

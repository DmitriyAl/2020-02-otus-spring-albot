package otus.spring.albot.lesson20.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.web.reactive.server.WebTestClient;
import otus.spring.albot.lesson20.business.AuthorService;
import otus.spring.albot.lesson20.dao.AuthorRepo;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.GenreRepo;
import otus.spring.albot.lesson20.dao.NoteRepo;
import otus.spring.albot.lesson20.entity.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
class AuthorControllerTest {
    @Autowired
    private WebTestClient webClient;
    @MockBean
    private AuthorService authorService;

    @Test
    void getAuthors() {
        Author pushkin = new Author("Pushkin");
        Author lermontov = new Author("Lermontov");
        given(authorService.findAllAuthors()).willReturn(Flux.just(pushkin, lermontov));
        webClient.get().uri("/authors").exchange().expectStatus().isOk()
                .expectBodyList(Author.class).hasSize(2).contains(pushkin, lermontov);
    }

    @Test
    void getAuthorById() {
        Author pushkin = new Author("Pushkin");
        given(authorService.findByName(pushkin.getName())).willReturn(Mono.just(pushkin));
        webClient.get().uri(b -> b.path("/author").queryParam("name", pushkin.getName()).build())
                .exchange().expectStatus().isOk().expectBody(Author.class).isEqualTo(pushkin);
    }

    @Test
    void saveAuthor() {
        Author before = new Author("Pushkin");
        Author after = new Author("Pushkin");
        after.setId("id");
        given(authorService.saveNewAuthor(before)).willReturn(Mono.just(after));
        webClient.post().uri("/authors").bodyValue(before)
                .exchange().expectStatus().isOk().expectBody(Author.class).isEqualTo(after);
    }

    @Test
    void renameAuthor() {
        Author newAuthor = new Author("Pushkin");
        given(authorService.changeAuthorName(newAuthor)).willReturn(Mono.just(newAuthor));
        webClient.put().uri("/authors").bodyValue(newAuthor)
                .exchange().expectStatus().isOk().expectBody(Author.class).isEqualTo(newAuthor);
    }

    @Test
    void deleteAuthor() {
        Author author = new Author("Pushkin");
        author.setId("id");
        given(authorService.removeAuthorById(author.getId())).willReturn(Mono.empty());
        webClient.delete().uri(b-> b.path("/authors").queryParam("id", author.getId()).build())
                .exchange().expectStatus().isOk().expectBody(Void.class);
    }
}

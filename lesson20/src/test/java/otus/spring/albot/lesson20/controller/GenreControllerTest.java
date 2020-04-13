package otus.spring.albot.lesson20.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import otus.spring.albot.lesson20.business.GenreService;
import otus.spring.albot.lesson20.entity.Genre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
class GenreControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private GenreService genreService;

    @Test
    void getAllGenres() {
        Genre novel = new Genre("Novel");
        Genre fairytale = new Genre("Fairytale");
        given(genreService.findAllGenres()).willReturn(Flux.just(novel, fairytale));
        webTestClient.get().uri("/genres")
                .exchange().expectStatus().isOk().expectBodyList(Genre.class).hasSize(2).contains(novel, fairytale);
    }

    @Test
    void getGenreByName() {
        Genre novel = new Genre("Novel");
        given(genreService.findByName(novel.getName())).willReturn(Mono.just(novel));
        webTestClient.get().uri(b -> b.path("/genre").queryParam("name", novel.getName()).build())
                .exchange().expectStatus().isOk().expectBody(Genre.class).isEqualTo(novel);
    }

    @Test
    void saveGenre() {
        Genre before = new Genre("Novel");
        Genre after = new Genre("Novel");
        after.setId("id");
        given(genreService.addNewGenre(before)).willReturn(Mono.just(after));
        webTestClient.post().uri("/genres").bodyValue(before)
                .exchange().expectStatus().isOk().expectBody(Genre.class).isEqualTo(after);
    }

    @Test
    void deleteGenre() {
        Genre novel = new Genre("Novel");
        novel.setId("id");
        given(genreService.removeGenreById(novel.getId())).willReturn(Mono.empty());
        webTestClient.delete().uri(b -> b.path("/genres").queryParam("id", novel.getId()).build())
                .exchange().expectStatus().isOk().expectBody(Void.class);
    }
}

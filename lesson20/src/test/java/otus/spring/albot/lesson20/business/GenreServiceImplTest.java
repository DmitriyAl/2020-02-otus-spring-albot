package otus.spring.albot.lesson20.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.GenreRepo;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Genre;
import otus.spring.albot.lesson20.exception.DependentBookException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class GenreServiceImplTest {
    @Autowired
    private GenreService genreService;
    @MockBean
    private GenreRepo genreRepo;

    @Test
    void removeGenreByIdWithDependentBook() {
        Genre novel = new Genre("Novel");
        novel.setId("id");
        novel.setBooks(Collections.singletonList(new Book()));
        given(genreRepo.findById(novel.getId())).willReturn(Mono.just(novel));
        Mono<Void> voidMono = genreService.removeGenreById(novel.getId());
        StepVerifier.create(voidMono).expectError(DependentBookException.class).verify();
    }

    @Test
    void removeGenreById() {
        Genre novel = new Genre("Novel");
        novel.setId("id");
        given(genreRepo.findById(novel.getId())).willReturn(Mono.just(novel));
        given(genreRepo.delete(novel)).willReturn(Mono.empty());
        Mono<Void> voidMono = genreService.removeGenreById(novel.getId());
        StepVerifier.create(voidMono).expectComplete().verify();
    }
}

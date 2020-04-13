package otus.spring.albot.lesson20.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.albot.lesson20.dao.AuthorRepo;
import otus.spring.albot.lesson20.entity.Author;
import otus.spring.albot.lesson20.exception.NoSuchAuthorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class AuthorServiceImplTest {
    @Autowired
    private AuthorService authorService;
    @MockBean
    private AuthorRepo authorRepo;

    @Test
    void findAllAuthors() {
        Author pushkin = new Author("Pushkin");
        Author lermontov = new Author("Lermontov");
        given(authorRepo.findAll()).willReturn(Flux.just(pushkin, lermontov));
        Flux<Author> allAuthors = authorService.findAllAuthors();
        StepVerifier.create(allAuthors).expectNext(pushkin, lermontov).expectComplete().verify();
    }

    @Test
    void saveNewAuthor() {
        Author before = new Author("Pushkin");
        Author after = new Author("Pushkin");
        after.setId("id");
        given(authorRepo.save(before)).willReturn(Mono.just(after));
        Mono<Author> authorMono = authorService.saveNewAuthor(before);
        StepVerifier.create(authorMono).expectNext(after).expectComplete().verify();
    }

    @Test
    void findByName() {
        Author pushkin = new Author("Pushkin");
        given(authorRepo.findByName(pushkin.getName())).willReturn(Mono.just(pushkin));
        Mono<Author> authorMono = authorService.findByName(pushkin.getName());
        StepVerifier.create(authorMono).expectNext(pushkin).expectComplete().verify();
    }

    @Test
    void removeAuthorById() {
        Author author = new Author("Pushkin");
        author.setId("id");
        given(authorRepo.deleteById(author.getId())).willReturn(Mono.empty());
        Mono<Void> mono = authorService.removeAuthorById(author.getId());
        StepVerifier.create(mono).expectComplete().verify();
    }

    @Test
    void changeAuthorName() {
        Author newAuthor = new Author("Pushkin");
        newAuthor.setId("id");
        given(authorRepo.save(newAuthor)).willReturn(Mono.just(newAuthor));
        given(authorRepo.findById(newAuthor.getId())).willReturn(Mono.just(newAuthor));
        Mono<Author> authorMono = authorService.changeAuthorName(newAuthor);
        StepVerifier.create(authorMono).expectNext(newAuthor).expectComplete().verify();
    }

    @Test
    void changeNotExistedAuthorName() {
        Author newAuthor = new Author("Pushkin");
        newAuthor.setId("id");
        given(authorRepo.findById(newAuthor.getId())).willReturn(Mono.empty());
        Mono<Author> authorMono = authorService.changeAuthorName(newAuthor);
        StepVerifier.create(authorMono).expectError(NoSuchAuthorException.class).verify();
    }
}

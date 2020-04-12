package otus.spring.albot.lesson20.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import otus.spring.albot.lesson20.business.AuthorService;
import otus.spring.albot.lesson20.entity.Author;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class AuthorController {
    private AuthorService authorService;

    @GetMapping(value = "authors")
    public Flux<Author> getAuthors() {
        return authorService.findAllAuthors();
    }

    @GetMapping(value = "author")
    public Mono<Author> getAuthorById(@RequestParam("name") String name) {
        return authorService.findByName(name);
    }

    @PostMapping(value = "authors")
    public Mono<Author> saveAuthor(@RequestBody Author author) {
        return authorService.saveNewAuthor(author);
    }

    @PutMapping(value = "authors")
    public Mono<String> renameAuthor(@RequestBody Author author) {
        return authorService.changeAuthorName(author.getId(), author.getName()).map(Author::getName);
    }

    @DeleteMapping(value = "authors")
    public Mono<Void> deleteAuthor(@RequestParam("id") String id) {
        return authorService.removeAuthorById(id);
    }
}

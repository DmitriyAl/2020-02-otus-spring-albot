package otus.spring.albot.lesson20.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import otus.spring.albot.lesson20.business.GenreService;
import otus.spring.albot.lesson20.entity.Genre;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class GenreController {
    private GenreService genreService;

    @GetMapping(value = "genres")
    public Flux<Genre> getAllGenres() {
        return genreService.findAllGenres();
    }

    @GetMapping(value = "genre")
    public Mono<Genre> getGenreByName(@RequestParam("name") String name) {
        return genreService.findByName(name);
    }

    @PostMapping(value = "genres")
    public Mono<Genre> saveGenre(@RequestBody Genre genre) {
        return genreService.addNewGenre(genre);
    }

    @DeleteMapping(value = "genres")
    public Mono<Void> deleteGenre(@RequestParam("id") String id) {
        return genreService.removeGenreById(id);
    }
}

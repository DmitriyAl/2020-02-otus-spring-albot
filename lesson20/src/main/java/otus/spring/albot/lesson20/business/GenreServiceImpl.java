package otus.spring.albot.lesson20.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson20.dao.GenreRepo;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Genre;
import otus.spring.albot.lesson20.exception.DependentBookException;
import otus.spring.albot.lesson20.exception.NoSuchGenreException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GenreServiceImpl implements GenreService {
    private GenreRepo genreRepo;

    @Override
    public Flux<Genre> findAllGenres() {
        return genreRepo.findAll();
    }

    @Override
    public Mono<Genre> findByName(String name) {
        return genreRepo.findByName(name);
    }

    @Override
    @Transactional
    public Mono<Genre> addNewGenre(Genre genre) {
        return genreRepo.save(genre);
    }

    @Override
    @Transactional
    public Mono<Void> removeGenreById(String id) {
        return genreRepo.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchGenreException(id)))
                .flatMap(this::checkDependentBook);
    }

    private Mono<Void> checkDependentBook(Genre genre) {
        if (genre.getBooks() != null && !genre.getBooks().isEmpty()) {
            StringBuilder sb =
                    new StringBuilder("The next list of books depends on this genre ('" + genre.getName() + "'):");
            for (Book book : genre.getBooks()) {
                sb.append("\n");
                sb.append(book.toString());
            }
            return Mono.error(new DependentBookException(sb.toString()));
        }
        return genreRepo.delete(genre);
    }
}

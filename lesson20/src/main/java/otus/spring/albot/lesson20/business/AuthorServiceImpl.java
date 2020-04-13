package otus.spring.albot.lesson20.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson20.dao.AuthorRepo;
import otus.spring.albot.lesson20.entity.Author;
import otus.spring.albot.lesson20.exception.NoSuchAuthorException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepo authorRepo;

    @Override
    public Flux<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Mono<Author> saveNewAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Mono<Author> findByName(String name) {
        return authorRepo.findByName(name);
    }

    @Override
    @Transactional
    public Mono<Void> removeAuthorById(String id) {
        return authorRepo.deleteById(id);
    }

    @Override
    public Mono<Author> changeAuthorName(Author author) {
        return authorRepo.findById(author.getId())
                .switchIfEmpty(Mono
                        .error(new NoSuchAuthorException(String.format("No such author with id: %s", author.getId()))))
                .map(a -> changeName(a, author.getName())).flatMap(authorRepo::save);
    }

    private Author changeName(Author author, String name) {
        author.setName(name);
        return author;
    }
}

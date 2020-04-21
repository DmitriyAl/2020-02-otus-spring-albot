package otus.spring.albot.lesson20.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson20.dao.AuthorRepo;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.GenreRepo;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.exception.NoSuchAuthorException;
import otus.spring.albot.lesson20.exception.NoSuchGenreException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final GenreRepo genreRepo;

    @Override
    public Flux<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Mono<Book> findByName(String name) {
        return bookRepo.findByName(name);
    }

    @Override
    public Mono<Book> findById(String id) {
        return bookRepo.findById(id);
    }

    @Override
    @Transactional
    public Flux<Book> findAllBooksByAuthorId(String authorId) {
        return authorRepo.findById(authorId)
                .switchIfEmpty(Mono.error(new NoSuchAuthorException(String.format("No author with such id: %s", authorId))))
                .flatMapMany(bookRepo::findAllByAuthor);
    }

    @Override
    @Transactional
    public Mono<Book> addNewBook(final Book book) {
        return authorRepo.findById(book.getAuthor().getId())
                .switchIfEmpty(Mono.error(new NoSuchAuthorException(String.format("No author with such Id: %s", book.getAuthor().getId()))))
                .zipWith(genreRepo.findById(book.getGenre().getId())
                        .switchIfEmpty(Mono.error(new NoSuchGenreException(String.format("No genre with such id: %s", book.getGenre().getId())))),
                        (author, genre) -> new Book(book.getName(), author, genre)).flatMap(bookRepo::save);
    }

    @Override
    @Transactional
    public Mono<Void> removeBookById(String id) {
        return bookRepo.deleteById(id);
    }
}

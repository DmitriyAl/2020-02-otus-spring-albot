package otus.spring.albot.lesson20.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.NoteRepo;
import otus.spring.albot.lesson20.entity.Note;
import otus.spring.albot.lesson20.exception.NoSuchBookException;
import otus.spring.albot.lesson20.exception.NoSuchNoteException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private BookRepo bookRepo;
    private NoteRepo noteRepo;

    @Override
    @Transactional
    public Mono<Note> addNoteToBook(String bookId, final String textNote) {
        return bookRepo.findById(bookId)
                .switchIfEmpty(Mono.error(new NoSuchBookException(String.format("No book with such id: %s", bookId))))
                .flatMap(book -> noteRepo.save(new Note(textNote, book)));
    }

    @Override
    @Transactional
    public Mono<Void> removeNote(String id) {
        return noteRepo.findById(id).switchIfEmpty(Mono.error(new NoSuchNoteException(id))).flatMap(noteRepo::delete);
    }

    @Override
    public Flux<Note> extractAllNotesForBookById(String id) {
        return bookRepo.findById(id)
                .switchIfEmpty(Mono.error(new NoSuchBookException(String.format("No book with such id: %s", id))))
                .flatMapMany(noteRepo::findByBook);
    }

    @Override
    public Flux<Note> extractAllNotesForBookByName(String name) {
        return bookRepo.findByName(name)
                .switchIfEmpty(Mono.error(new NoSuchBookException(String.format("No book with such name: %s", name))))
                .flatMapMany(noteRepo::findByBook);
    }
}

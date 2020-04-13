package otus.spring.albot.lesson20.business;

import otus.spring.albot.lesson20.entity.Note;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NoteService {
    Mono<Note> addNoteToBook(Note note);

    Mono<Void> removeNote(String id);

    Flux<Note> extractAllNotesForBookById(String id);

    Flux<Note> extractAllNotesForBookByName(String name);
}

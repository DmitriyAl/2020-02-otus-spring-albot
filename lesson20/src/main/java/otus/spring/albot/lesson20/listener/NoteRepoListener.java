package otus.spring.albot.lesson20.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.NoteRepo;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Note;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Component
@AllArgsConstructor
public class NoteRepoListener extends AbstractMongoEventListener<Note> {
    private BookRepo bookRepo;
    private NoteRepo noteRepo;

    @Override
    public void onAfterSave(AfterSaveEvent<Note> event) {
        super.onAfterSave(event);
        final Note note = event.getSource();
        if (note.getBook().getNotes() == null) {
            note.getBook().setNotes(new ArrayList<>());
        }
        note.getBook().getNotes().add(note);
        bookRepo.save(note.getBook()).subscribe();
    }

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Note> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        noteRepo.findById(id).flatMap(this::updateBookNotes).subscribe();
    }

    private Mono<Book> updateBookNotes(Note note) {
        if (note.getBook() != null) {
            return bookRepo.findById(note.getBook().getId()).flatMap(bookRepo::save);
        }
        return Mono.empty();
    }
}

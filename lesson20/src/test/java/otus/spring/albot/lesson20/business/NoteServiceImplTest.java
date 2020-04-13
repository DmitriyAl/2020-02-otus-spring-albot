package otus.spring.albot.lesson20.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.dao.NoteRepo;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Note;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class NoteServiceImplTest {
    @Autowired
    private NoteService noteService;
    @MockBean
    private NoteRepo noteRepo;
    @MockBean
    private BookRepo bookRepo;

    @Test
    void addNoteToBook() {
        Book book = new Book();
        book.setId("id");
        Note note = new Note("Note", book);
        given(bookRepo.findById(book.getId())).willReturn(Mono.just(book));
        given(noteRepo.save(note)).willReturn(Mono.just(note));
        Mono<Note> noteMono = noteService.addNoteToBook(note);
        StepVerifier.create(noteMono).expectNext(note).expectComplete().verify();
    }

    @Test
    void removeNote() {
        Book book = new Book();
        book.setId("id");
        Note note = new Note("Note", book);
        note.setId("id");
        given(noteRepo.findById(note.getId())).willReturn(Mono.just(note));
        given(noteRepo.delete(note)).willReturn(Mono.empty());
        Mono<Void> voidMono = noteService.removeNote(note.getId());
        StepVerifier.create(voidMono).expectComplete().verify();
    }

    @Test
    void extractAllNotesForBookById() {
        Book book = new Book();
        book.setId("id");
        Note note = new Note("Note", book);
        note.setId("id");
        given(bookRepo.findById(book.getId())).willReturn(Mono.just(book));
        given(noteRepo.findByBook(book)).willReturn(Flux.just(note));
        Flux<Note> noteFlux = noteService.extractAllNotesForBookById(book.getId());
        StepVerifier.create(noteFlux).expectNext(note).expectComplete().verify();
    }

    @Test
    void extractAllNotesForBookByName() {
        Book book = new Book();
        book.setName("Book");
        Note note = new Note("Note", book);
        note.setId("id");
        given(bookRepo.findByName(book.getName())).willReturn(Mono.just(book));
        given(noteRepo.findByBook(book)).willReturn(Flux.just(note));
        Flux<Note> noteFlux = noteService.extractAllNotesForBookByName(book.getName());
        StepVerifier.create(noteFlux).expectNext(note).expectComplete().verify();
    }
}

package otus.spring.albot.lesson20.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import otus.spring.albot.lesson20.business.NoteService;
import otus.spring.albot.lesson20.entity.Book;
import otus.spring.albot.lesson20.entity.Note;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@AutoConfigureWebTestClient
class NoteControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private NoteService noteService;

    @Test
    void addNoteToBook() {
        Book book = new Book();
        Note note = new Note("Note", book);
        given(noteService.addNoteToBook(note)).willReturn(Mono.just(note));
        webTestClient.post().uri("/notes").bodyValue(note)
                .exchange().expectStatus().isOk().expectBody(Note.class).isEqualTo(note);
    }

    @Test
    void deleteNote() {
        Book book = new Book();
        Note note = new Note("Note", book);
        note.setId("id");
        given(noteService.removeNote(note.getId())).willReturn(Mono.empty());
        webTestClient.delete().uri(b -> b.path("/notes").queryParam("id", note.getId()).build())
                .exchange().expectStatus().isOk().expectBody(Void.class);
    }

    @Test
    void getNotesToBookById() {
        Book book = new Book();
        book.setId("id");
        Note note = new Note("Note", book);
        given(noteService.extractAllNotesForBookById(book.getId())).willReturn(Flux.just(note));
        webTestClient.get().uri(b -> b.path("/notes").queryParam("bookId", book.getId()).build())
                .exchange().expectStatus().isOk().expectBodyList(Note.class).contains(note).hasSize(1);
    }
}

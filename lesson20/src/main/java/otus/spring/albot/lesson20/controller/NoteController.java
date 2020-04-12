package otus.spring.albot.lesson20.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import otus.spring.albot.lesson20.business.NoteService;
import otus.spring.albot.lesson20.entity.Note;
import otus.spring.albot.lesson20.exception.GetNotesException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class NoteController {
    private NoteService noteService;

    @PostMapping(value = "notes")
    public Mono<Note> addNoteToBook(@RequestParam("bookId") String bookId, @RequestParam("note") String note) {
        return noteService.addNoteToBook(bookId, note);
    }

    @DeleteMapping(value = "notes")
    public Mono<Void> deleteNote(@RequestParam("id") String id) {
        return noteService.removeNote(id);
    }

    @GetMapping(value = "notes")
    public Flux<Note> getNotesToBook(@RequestParam(value = "bookId", required = false) String bookId,
                                     @RequestParam(value = "bookName", required = false) String bookName) {
        if (bookId != null && bookName == null) {
            return noteService.extractAllNotesForBookById(bookId);
        } else if (bookId == null && bookName != null) {
            return noteService.extractAllNotesForBookByName(bookName);
        } else {
            return Flux.error(GetNotesException::new);
        }
    }
}

package otus.spring.albot.lesson17.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import otus.spring.albot.lesson17.dto.NoteDto;
import otus.spring.albot.lesson17.exception.NoSuchProductException;
import otus.spring.albot.lesson17.service.NoteService;

import java.util.List;

@RestController
@AllArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/notes/{id}")
    public List<NoteDto> getNotesForProduct(@PathVariable("id") long productId) {
        try {
            return noteService.getNotesToProduct(productId);
        } catch (NoSuchProductException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getCode().getCode());
        }
    }

    @PostMapping(value = "notes")
    public NoteDto addNote(@RequestBody NoteDto noteDto) {
        return noteService.addNote(noteDto);
    }

    @DeleteMapping(value = "notes")
    public void removeNote(@RequestParam("id") long id) {
        noteService.removeNote(id);
    }
}

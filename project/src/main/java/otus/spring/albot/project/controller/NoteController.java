package otus.spring.albot.project.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import otus.spring.albot.project.dto.NoteDto;
import otus.spring.albot.project.exception.NoSuchProductException;
import otus.spring.albot.project.service.NoteService;

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

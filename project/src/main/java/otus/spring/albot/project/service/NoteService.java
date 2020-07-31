package otus.spring.albot.project.service;

import otus.spring.albot.project.dto.NoteDto;
import otus.spring.albot.project.exception.NoSuchProductException;

import java.util.List;

public interface NoteService {
    List<NoteDto> getNotesToProduct(long productId) throws NoSuchProductException;

    NoteDto addNote(NoteDto noteDto);

    void removeNote(long id);
}

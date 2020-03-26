package otus.spring.albot.lesson17.service;

import otus.spring.albot.lesson17.dto.NoteDto;
import otus.spring.albot.lesson17.exception.NoSuchProductException;

import java.util.List;

public interface NoteService {
    List<NoteDto> getNotesToProduct(long productId) throws NoSuchProductException;
}

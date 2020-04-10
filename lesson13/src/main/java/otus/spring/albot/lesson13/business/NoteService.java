package otus.spring.albot.lesson13.business;

import otus.spring.albot.lesson13.entity.Note;
import otus.spring.albot.lesson13.exception.NoSuchNoteException;

public interface NoteService {
    Note addNoteToBook(String bookId, String note);

    void removeNote(String id) throws NoSuchNoteException;
}

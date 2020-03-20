package otus.spring.albot.lesson11.business;

import otus.spring.albot.lesson11.entity.Note;
import otus.spring.albot.lesson11.exception.NoSuchNoteException;

/**
 * <pre>
 * $Id: $
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * </pre>
 *
 * @author Dmitrii Albot
 */
public interface NoteService {
    Note addNoteToBook(long bookId, String note);

    void removeNote(long id) throws NoSuchNoteException;
}
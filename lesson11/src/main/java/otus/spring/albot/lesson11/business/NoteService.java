package otus.spring.albot.lesson11.business;

import otus.spring.albot.lesson11.entity.Book;

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
    Book addNoteToBook(long bookId, String note);

    void removeNote(long id);
}
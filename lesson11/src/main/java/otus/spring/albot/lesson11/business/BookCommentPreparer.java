package otus.spring.albot.lesson11.business;

import otus.spring.albot.lesson11.model.BookNotes;

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
public interface BookCommentPreparer {
    BookNotes extractAllNotesForBookById(long id);
    BookNotes extractAllNotesForBookByName(String template);
}
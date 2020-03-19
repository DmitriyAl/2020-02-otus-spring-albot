package otus.spring.albot.lesson9.business;


import otus.spring.albot.lesson9.entity.Book;

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
public interface CommentAddingService {
    Book addCommentToBook(long bookId, String comment);
}

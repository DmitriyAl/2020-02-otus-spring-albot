package otus.spring.albot.lesson9.business;

import otus.spring.albot.lesson9.entity.Comment;

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
public interface CommentService {
    Comment addCommentToBook(long bookId, String comment);

    void removeComment(long id);
}

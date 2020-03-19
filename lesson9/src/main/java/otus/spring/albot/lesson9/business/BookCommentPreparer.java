package otus.spring.albot.lesson9.business;

import otus.spring.albot.lesson9.model.BookComments;

import java.util.List;

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
    BookComments extractAllCommentsForBookById(long id);

    List<BookComments> extractAllCommentsForBookByTemplate(String template);
}

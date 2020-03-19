package otus.spring.albot.lesson9.dao;

import otus.spring.albot.lesson9.entity.Book;
import otus.spring.albot.lesson9.entity.Comment;

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

public interface CommentDao {

    List<Comment> getCommentsForBook(Book book);

    void addNewComment(Comment comment);

    Comment getCommentById(long id);

    void removeCommentById(long id);
}

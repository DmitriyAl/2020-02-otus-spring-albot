package otus.spring.albot.lesson9.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson9.dao.BookDao;
import otus.spring.albot.lesson9.dao.CommentDao;
import otus.spring.albot.lesson9.entity.Book;
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
@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private BookDao bookDao;
    private CommentDao commentDao;

    @Override
    @Transactional
    public Comment addCommentToBook(long bookId, String textComment) {
        Book book = bookDao.findBookById(bookId);
        Comment comment = new Comment(textComment, book);
        commentDao.addNewComment(comment);
        return comment;
    }

    @Override
    @Transactional
    public void removeComment(long id) {
        commentDao.removeCommentById(id);
    }
}

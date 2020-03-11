package otus.spring.albot.lesson9.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson9.dao.BookDao;
import otus.spring.albot.lesson9.dao.CommentDao;
import otus.spring.albot.lesson9.entity.Book;
import otus.spring.albot.lesson9.entity.Comment;
import otus.spring.albot.lesson9.model.BookComments;

import java.util.ArrayList;
import java.util.Collections;
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
@Service
@AllArgsConstructor
public class BookCommentPreparerImpl implements BookCommentPreparer {
    private BookDao bookDao;
    private CommentDao commentDao;

    @Override
    @Transactional
    public BookComments extractAllCommentsForBookById(long id) {
        Book book = bookDao.findBookById(id);
        List<Comment> commentsForBook = commentDao.getCommentsForBook(book);
        return fillInBookComments(book, commentsForBook);
    }

    @Override
    @Transactional
    public List<BookComments> extractAllCommentsForBookByTemplate(String template) {
        List<Book> books = bookDao.findBookByName(template);
        if (books.isEmpty()) {
            return Collections.emptyList();
        }
        List<BookComments> bookComments = new ArrayList<>();
        for (Book book : books) {
            bookComments.add(extractAllCommentsForBookById(book.getId()));
        }
        return bookComments;
    }

    private BookComments fillInBookComments(Book book, List<Comment> comments) {
        return new BookComments(book.getName(), comments);
    }
}

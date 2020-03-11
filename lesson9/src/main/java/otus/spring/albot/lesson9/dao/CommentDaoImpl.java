package otus.spring.albot.lesson9.dao;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson9.entity.Book;
import otus.spring.albot.lesson9.entity.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

@Repository
@AllArgsConstructor
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<Comment> getCommentsForBook(Book book) {
        return em.createQuery("select c from Comment c where c.book = :book", Comment.class).setParameter("book", book).getResultList();
    }

    @Override
    @Transactional
    public void addNewComment(Comment comment) {
        em.persist(comment);
    }

    @Override
    @Transactional
    public Comment getCommentById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    @Transactional
    public void removeCommentById(long id) {
        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }
}

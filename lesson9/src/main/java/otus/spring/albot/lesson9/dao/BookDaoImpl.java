package otus.spring.albot.lesson9.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson9.entity.Book;

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
public class BookDaoImpl implements BookDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Book findBookById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    @Transactional
    public List<Book> findAllBooks() {
        return em.createQuery("select b from Book b").getResultList();
    }

    @Override
    @Transactional
    public List<Book> findBookByName(String template) {
        return em.createQuery("select b from Book b where b.name like :template").setParameter("template", "%" + template + "%").getResultList();
    }

    @Override
    @Transactional
    public void addNewBook(Book book) {
        em.persist(book);
    }

    @Override
    @Transactional
    public void deleteBook(long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }
}

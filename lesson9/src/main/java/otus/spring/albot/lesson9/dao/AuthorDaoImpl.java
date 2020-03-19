package otus.spring.albot.lesson9.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson9.entity.Author;

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
public class AuthorDaoImpl implements AuthorDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> findAllAuthors() {
        return em.createQuery("select a from Author a order by a.id").getResultList();
    }

    @Override
    public List<Author> findAuthorByTemplate(String template) {
        return em.createQuery("select a from Author a where a.name like :template").setParameter("template", "%" + template + "%").getResultList();
    }

    @Override
    @Transactional
    public Author findById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    @Transactional
    public void addNewAuthor(String name) {
        em.persist(new Author(name));
    }

    @Override
    @Transactional
    public void deleteAuthor(long id) {
        Author author = em.find(Author.class, id);
        em.remove(author);
    }

    @Override
    @Transactional
    public Author changeAuthorName(long id, String newName) {
        Author author = em.find(Author.class, id);
        author.setName(newName);
        return em.merge(author);
    }
}

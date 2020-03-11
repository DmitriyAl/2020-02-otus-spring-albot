package otus.spring.albot.lesson9.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson9.entity.Genre;

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
public class GenreDaoImpl implements GenreDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Genre> findAllGenres() {
        return em.createQuery("select g from Genre g").getResultList();
    }

    @Override
    public List<Genre> findAllGenresByName(String template) {
        return em.createQuery("select g from Genre g where g.name like :template").setParameter("template", "%" + template + "%").getResultList();
    }

    @Override
    public Genre findById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    @Transactional
    public void addNewGenre(String name) {
        em.persist(new Genre(name));
    }

    @Override
    @Transactional
    public void deleteGenre(long id) {
        Genre genre = em.find(Genre.class, id);
        em.remove(genre);
    }
}

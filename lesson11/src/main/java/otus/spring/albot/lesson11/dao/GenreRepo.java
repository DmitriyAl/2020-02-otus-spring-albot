package otus.spring.albot.lesson11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson11.entity.Genre;

import java.util.Optional;

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
public interface GenreRepo extends JpaRepository<Genre, Long> {
    Optional<Genre> findByName(String name);
}
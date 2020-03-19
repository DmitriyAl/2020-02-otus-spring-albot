package otus.spring.albot.lesson11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson11.entity.Author;

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
public interface AuthorRepo extends JpaRepository<Author, Long> {
    Optional<Author> findByName(String name);
}
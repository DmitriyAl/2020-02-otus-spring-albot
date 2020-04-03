package otus.spring.albot.lesson11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson11.entity.Book;

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
public interface BookRepo extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String name);
}
package otus.spring.albot.lesson11.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Note;

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
public interface NoteRepo extends JpaRepository<Note, Long> {
    List<Note> findByBook(Book book);
}
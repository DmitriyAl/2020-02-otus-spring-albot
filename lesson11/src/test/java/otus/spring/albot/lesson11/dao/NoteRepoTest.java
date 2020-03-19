package otus.spring.albot.lesson11.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Note;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
@DataJpaTest
public class NoteRepoTest {
    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private NoteRepo noteRepo;

    @Test
    public void findByBook() {
        Book book = bookRepo.findById(1L).orElseThrow(NullPointerException::new);
        List<Note> comments = noteRepo.findByBook(book);
        assertThat(comments).allMatch(c-> c.getBook().equals(book));
    }
}
package otus.spring.albot.lesson11.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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
//        Optional<Book> bookOptional = bookRepo.findById(1L);
//        Book book = bookOptional.orElse(null);
//        List<Comment> comments = commentRepo.findByBook(book);
//        assertThat(comments).allMatch(c-> c.getBook().equals(book));
    }
}
package otus.spring.albot.lesson7.repo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import otus.spring.albot.lesson7.entity.Book;

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
@JdbcTest
@ExtendWith(SpringExtension.class)
@Import(BookRepoImpl.class)
public class BookRepoImplTest {
    @Autowired
    private BookRepo bookRepo;

    @Test
    public void findBookByName() throws Exception {
        assertThat(bookRepo.findBookByName("fish")).hasSize(1)
                .containsOnly(new Book(3, "The story about the fisherman and the golden fish"));
    }

    @Test
    public void findAllBooks() throws Exception {
        assertThat(bookRepo.findAllBooks()).hasSize(4).allMatch(book -> book.getName() != null)
                .allMatch(book -> book.getId() != 0);
    }

}
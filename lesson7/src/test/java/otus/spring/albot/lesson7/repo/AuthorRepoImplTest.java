package otus.spring.albot.lesson7.repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import otus.spring.albot.lesson7.entity.Author;

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
@RunWith(SpringRunner.class)
@Import(AuthorRepoImpl.class)
public class AuthorRepoImplTest {
    @Autowired
    private AuthorRepo authorRepo;

    @Test
    public void findAllAuthors() throws Exception {
        assertThat(authorRepo.findAllAuthors()).hasSize(3).allMatch(author -> author.getName() != null)
                .allMatch(author -> author.getId() != 0);
    }

    @Test
    public void findAuthorByName() throws Exception {
        assertThat(authorRepo.findAuthorByName("sh")).hasSize(1).containsOnly(new Author(1, "Alexander Pushkin"));
    }
}
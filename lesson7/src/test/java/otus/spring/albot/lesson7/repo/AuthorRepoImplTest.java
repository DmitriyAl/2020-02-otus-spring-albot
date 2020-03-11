package otus.spring.albot.lesson7.repo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson7.entity.Author;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The tests launch without transactions. One test refresh the context before its launching
 *
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
@Import(AuthorRepoImpl.class)
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@DisplayName("DAO for authors")
public class AuthorRepoImplTest {
    @Autowired
    private AuthorRepo authorRepo;

    @Test
    public void addAuthor() {
        assertThat(authorRepo.saveAuthor("Mikhail Bulgakov")).isEqualTo(1);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public void findAllAuthors() throws Exception {
        assertThat(authorRepo.findAllAuthors()).hasSize(3).allMatch(author -> author.getName() != null)
                .allMatch(author -> author.getId() != 0);
    }

    @Test
    public void findAuthorByName() throws Exception {
        assertThat(authorRepo.findAuthorByName("sh")).hasSize(1).containsOnly(new Author(1, "Alexander Pushkin"));
    }
}
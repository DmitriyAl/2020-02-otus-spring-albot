package otus.spring.albot.lesson9.dao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

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
@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(AuthorDaoImpl.class)
public class AuthorDaoImplTest {
    @Autowired
    private AuthorDao authorDao;

    @Test
    public void findAllAuthors() throws Exception {
        assertThat(authorDao.findAllAuthors()).hasSize(4).allMatch(author -> author.getName() != null)
                .allMatch(author -> author.getId() != 0);
    }

    @Test
    public void findAuthorByName() throws Exception {
        assertThat(authorDao.findAuthorByTemplate("sh")).hasSize(1).allMatch(author -> author.getName() != null)
                .allMatch(author -> author.getName().equals("Alexander Pushkin"));
    }
}
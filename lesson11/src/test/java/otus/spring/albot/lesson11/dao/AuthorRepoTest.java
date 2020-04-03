package otus.spring.albot.lesson11.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import otus.spring.albot.lesson11.entity.Author;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepoTest {
    @Autowired
    private AuthorRepo authorRepo;
    @Autowired
    private BookRepo bookRepo;

    @Test
    public void removeAuthorWithAllBooks() {
        Author author = authorRepo.findById(1L).orElseThrow(NullPointerException::new);
        int bookByAuthor = author.getBooks().size();
        int totalBookAmount = bookRepo.findAll().size();
        authorRepo.delete(author);
        assertThat(bookRepo.findAll())
                .hasSize(totalBookAmount - bookByAuthor)
                .noneMatch(b -> b.getAuthor().getId() == author.getId());
    }

    @Test
    void findByName() {
        Author author = authorRepo.findById(1L).orElseThrow(NullPointerException::new);
        String authorName = author.getName();
        Author authorByName = authorRepo.findByName(authorName).orElseThrow(NullPointerException::new);
        assertThat(author).isEqualTo(authorByName);
    }
}
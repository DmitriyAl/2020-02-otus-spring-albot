package otus.spring.albot.lesson13.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson13.dao.AuthorRepo;
import otus.spring.albot.lesson13.dao.BookRepo;
import otus.spring.albot.lesson13.entity.Author;

@Component
@AllArgsConstructor
public class AuthorRepoListener extends AbstractMongoEventListener<Author> {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        authorRepo.findById(id).ifPresent(this::removeAuthorBooks);
    }

    private void removeAuthorBooks(Author author) {
        if (author.getBooks() != null) {
            author.getBooks().forEach(b -> bookRepo.deleteById(b.getId()));
        }
    }
}

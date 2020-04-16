package otus.spring.albot.lesson25.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson25.dao.AuthorRepo;
import otus.spring.albot.lesson25.dao.BookRepo;
import otus.spring.albot.lesson25.document.AuthorDoc;

@Component
@AllArgsConstructor
public class AuthorRepoListener extends AbstractMongoEventListener<AuthorDoc> {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<AuthorDoc> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        authorRepo.findById(id).ifPresent(this::removeAuthorBooks);
    }

    private void removeAuthorBooks(AuthorDoc author) {
        if (author.getBooks() != null) {
            author.getBooks().forEach(b -> bookRepo.deleteById(b.getId()));
        }
    }
}

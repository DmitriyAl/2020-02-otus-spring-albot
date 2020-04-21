package otus.spring.albot.lesson20.listener;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import otus.spring.albot.lesson20.dao.AuthorRepo;
import otus.spring.albot.lesson20.dao.BookRepo;
import otus.spring.albot.lesson20.entity.Author;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class AuthorRepoListener extends AbstractMongoEventListener<Author> {
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        String id = event.getSource().get("_id").toString();
        authorRepo.findById(id).flatMap(this::removeAuthorBooks).subscribe();
    }

    private Mono<Void> removeAuthorBooks(Author author) {
        if (author.getBooks() != null) {
            return bookRepo.deleteAll(author.getBooks());
        }
        return Mono.empty();
    }
}

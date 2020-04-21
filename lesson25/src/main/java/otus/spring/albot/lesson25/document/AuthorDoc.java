package otus.spring.albot.lesson25.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import otus.spring.albot.lesson25.entity.AuthorJpa;

import java.util.List;

@Document(collection = "authors")
@Data
@NoArgsConstructor
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "id")
public class AuthorDoc {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @DBRef
    private List<BookDoc> books;

    public AuthorDoc(String name) {
        this.name = name;
    }

    public static AuthorDoc fromJpa(AuthorJpa authorJpa) {
        AuthorDoc authorDoc = new AuthorDoc();
        authorDoc.id = String.valueOf(authorJpa.getId());
        authorDoc.name = authorJpa.getName();
        return authorDoc;
    }
}

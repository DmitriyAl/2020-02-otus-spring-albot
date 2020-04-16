package otus.spring.albot.lesson25.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import otus.spring.albot.lesson25.entity.GenreJpa;

import java.util.List;

@Document(collection = "genres")
@Data
@NoArgsConstructor
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "id")
public class GenreDoc {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @DBRef
    private List<BookDoc> books;

    public GenreDoc(String name) {
        this.name = name;
    }

    public static GenreDoc fromJpa(GenreJpa genreJpa) {
        GenreDoc genreDoc = new GenreDoc();
        genreDoc.id = String.valueOf(genreJpa.getId());
        genreDoc.name = genreJpa.getName();
        return genreDoc;
    }
}

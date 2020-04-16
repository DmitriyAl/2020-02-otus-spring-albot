package otus.spring.albot.lesson25.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import otus.spring.albot.lesson25.entity.BookJpa;

import java.util.List;

@Document(collection = "books")
@Data
@NoArgsConstructor
@ToString(exclude = "notes")
@EqualsAndHashCode(of = "id")
public class BookDoc {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @DBRef
    private AuthorDoc author;
    @DBRef
    private GenreDoc genre;
    @DBRef
    private List<NoteDoc> notes;

    public BookDoc(String name, AuthorDoc author, GenreDoc genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public static BookDoc fromJpa(BookJpa bookJpa) {
        BookDoc bookDoc = new BookDoc();
        bookDoc.id = String.valueOf(bookJpa.getId());
        bookDoc.name = bookJpa.getName();
        bookDoc.author = AuthorDoc.fromJpa(bookJpa.getAuthor());
        bookDoc.genre = GenreDoc.fromJpa(bookJpa.getGenre());
        return bookDoc;
    }
}

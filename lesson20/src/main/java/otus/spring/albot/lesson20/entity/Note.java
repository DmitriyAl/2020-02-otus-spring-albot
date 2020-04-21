package otus.spring.albot.lesson20.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "notes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Note {
    @Id
    private String id;
    @Field(name = "note")
    private String note;
    @DBRef
    private Book book;

    public Note(String note, Book book) {
        this.note = note;
        this.book = book;
    }
}

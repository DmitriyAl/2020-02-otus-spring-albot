package otus.spring.albot.lesson20.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "books")
@Data
@NoArgsConstructor
@ToString(exclude = "notes")
@EqualsAndHashCode(of = "id")
@JsonIgnoreProperties(value = "notes")
public class Book {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @DBRef
    private Author author;
    @DBRef
    private Genre genre;
    @DBRef
    private List<Note> notes;

    public Book(String name, Author author, Genre genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}

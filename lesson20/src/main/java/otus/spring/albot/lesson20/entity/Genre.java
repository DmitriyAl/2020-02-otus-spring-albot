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

@Document(collection = "genres")
@Data
@NoArgsConstructor
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "id")
@JsonIgnoreProperties(value = "books")
public class Genre {
    @Id
    private String id;
    @Field(name = "name")
    private String name;
    @DBRef
    private List<Book> books;

    public Genre(String name) {
        this.name = name;
    }
}

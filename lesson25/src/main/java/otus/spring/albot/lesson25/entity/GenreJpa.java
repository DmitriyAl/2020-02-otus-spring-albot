package otus.spring.albot.lesson25.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "genres")
@ToString(exclude = "books")
@EqualsAndHashCode(exclude = "id")
public class GenreJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "genre")
    private List<BookJpa> books;

    public GenreJpa(String name) {
        this.name = name;
    }
}

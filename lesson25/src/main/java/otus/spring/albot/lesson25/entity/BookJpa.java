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
@Table(name = "books")
@ToString(exclude = "notes")
@EqualsAndHashCode(exclude = "id")
public class BookJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @ManyToOne(targetEntity = AuthorJpa.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private AuthorJpa author;
    @ManyToOne(targetEntity = GenreJpa.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private GenreJpa genre;
    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<NoteJpa> notes;

    public BookJpa(String name, AuthorJpa author, GenreJpa genre) {
        this.name = name;
        this.author = author;
        this.genre = genre;
    }
}

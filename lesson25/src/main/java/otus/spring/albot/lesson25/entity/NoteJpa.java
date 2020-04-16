package otus.spring.albot.lesson25.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class NoteJpa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "note")
    private String note;
    @ManyToOne(targetEntity = BookJpa.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private BookJpa book;

    public NoteJpa(String note, BookJpa book) {
        this.note = note;
        this.book = book;
    }
}

package otus.spring.albot.lesson11.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * <pre>
 * $Id: $
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * </pre>
 *
 * @author Dmitrii Albot
 */
@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "note")
    private String note;
    @ManyToOne(targetEntity = Book.class, cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public Note(String note, Book book) {
        this.note = note;
        this.book = book;
    }
}
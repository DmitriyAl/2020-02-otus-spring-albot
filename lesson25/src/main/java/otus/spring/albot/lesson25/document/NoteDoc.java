package otus.spring.albot.lesson25.document;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import otus.spring.albot.lesson25.entity.NoteJpa;

@Document(collection = "notes")
@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = "id")
public class NoteDoc {
    @Id
    private String id;
    @Field(name = "note")
    private String note;
    @DBRef
    private BookDoc book;

    public NoteDoc(String note, BookDoc book) {
        this.note = note;
        this.book = book;
    }

    public static NoteDoc fromJpa(NoteJpa noteJpa) {
        NoteDoc noteDoc = new NoteDoc();
        noteDoc.id = String.valueOf(noteJpa.getId());
        noteDoc.note = noteJpa.getNote();
        noteDoc.book = BookDoc.fromJpa(noteJpa.getBook());
        return noteDoc;
    }
}

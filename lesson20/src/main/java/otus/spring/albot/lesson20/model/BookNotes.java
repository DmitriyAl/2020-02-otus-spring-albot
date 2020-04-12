package otus.spring.albot.lesson20.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import otus.spring.albot.lesson20.entity.Note;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookNotes {
    private String book;
    private List<Note> notes;

    @Override
    public String toString() {
        return "The book \"" + book + "\" has the next list of comments: " + formatNotes();
    }

    private String formatNotes() {
        if (!notes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            formatNote(sb, notes.get(0));
            for (int i = 1; i < notes.size(); i++) {
                sb.append(", ");
                formatNote(sb, notes.get(i));
            }
            sb.append("]");
            return sb.toString();
        }
        return "[**no notes**]";
    }

    private void formatNote(StringBuilder stringBuilder, Note note) {
        stringBuilder.append(String.format("\"(id=%s) %s\"", note.getId(), note.getNote()));
    }
}

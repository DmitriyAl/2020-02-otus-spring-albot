package otus.spring.albot.lesson17.dto;

import lombok.Data;
import otus.spring.albot.lesson17.entity.Note;

@Data
public class NoteDto {
    private long id;
    private String comment;
    private ProductDto product;
    private int rate;

    public static NoteDto fromDao(Note note) {
        if (note == null) {
            return null;
        }
        NoteDto dto = new NoteDto();
        dto.id = note.getId();
        dto.comment = note.getComment();
        dto.product = ProductDto.fromDao(note.getProduct());
        dto.rate = note.getRate();
        return dto;
    }
}

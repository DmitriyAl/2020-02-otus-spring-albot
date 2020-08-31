package otus.spring.albot.project.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import otus.spring.albot.project.dto.NoteDto;
import otus.spring.albot.project.entity.Note;
import otus.spring.albot.project.entity.Product;
import otus.spring.albot.project.repo.NoteRepo;
import otus.spring.albot.project.repo.ProductRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@SpringBootTest
class NoteServiceImplTest {
    @Autowired
    private NoteService noteService;
    @MockBean
    private ProductRepo productRepo;
    @MockBean
    private NoteRepo noteRepo;

    @Test
    void getNotesToProduct() throws Exception {
        Note note = new Note(1L, "Test", null, 0);
        NoteDto dto = NoteDto.fromDao(note);
        List<Note> notes = Collections.singletonList(note);
        Product product = new Product(1L, "Test", "Test", notes, null);
        given(productRepo.findById(1L)).willReturn(Optional.of(product));
        assertThat(noteService.getNotesToProduct(1L)).hasSize(1).containsOnly(dto);
    }

    @Test
    void addNote() {
        Product product = new Product(1L, "Test", "Test", null, null);
        Note inputNote = new Note(0L, "Test", product, 0);
        Note outputNote = new Note(1L, "Test", product, 0);
        NoteDto dto = NoteDto.fromDao(outputNote);
        given(noteRepo.save(inputNote)).willReturn(outputNote);
        assertThat(noteService.addNote(NoteDto.fromDao(inputNote))).isEqualTo(dto);
    }
}

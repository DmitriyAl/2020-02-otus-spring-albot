package otus.spring.albot.project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import otus.spring.albot.project.dto.NoteDto;
import otus.spring.albot.project.entity.Note;
import otus.spring.albot.project.entity.Product;
import otus.spring.albot.project.service.NoteService;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
class NoteControllerTest {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private NoteService noteService;

    @Test
    void getNotesForProduct() throws Exception {
        List<NoteDto> dtos = Collections.singletonList(NoteDto.fromDao(new Note(1L, "Test",
                new Product(1L, "Test", "Test", null, null), 0)));
        given(noteService.getNotesToProduct(1L)).willReturn(dtos);
        MvcResult mvcResult = this.mvc.perform(get("/notes/{id}", 1L))
                .andExpect(status().isOk()).andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(dtos)).isEqualTo(response);
    }

    @Test
    void addNote() throws Exception {
        NoteDto inputNoteDto = NoteDto.fromDao(new Note(0L, "Test",
                new Product(1L, "Test", "Test", null, null), 0));
        NoteDto outputNoteDto = NoteDto.fromDao(new Note(1L, "Test",
                new Product(1L, "Test", "Test", null, null), 0));
        given(noteService.addNote(inputNoteDto)).willReturn(outputNoteDto);
        MvcResult mvcResult = this.mvc.perform(post("/notes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputNoteDto)))
                .andExpect(status().isOk())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        assertThat(objectMapper.writeValueAsString(outputNoteDto)).isEqualTo(response);
    }

    @Test
    void removeNote() throws Exception {
        this.mvc.perform(delete("/notes").param("id", "1")).andExpect(status().isOk());
    }
}

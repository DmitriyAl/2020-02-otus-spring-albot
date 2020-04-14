package otus.spring.albot.lesson24.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import otus.spring.albot.lesson24.repo.NoteRepo;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doNothing;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class NoteServiceImplTest {
    @Autowired
    private NoteService noteService;
    @MockBean
    private NoteRepo noteRepo;

    @Test
    @WithMockUser(username = "admin", authorities = "ROLE_ADMIN")
    void removeNoteAdmin() {
        doNothing().when(noteRepo).deleteById(1L);
        assertThatCode(() -> noteService.removeNote(1L)).doesNotThrowAnyException();
    }

    @Test
    @WithMockUser(username = "user", authorities = "ROLE_USER")
    void removeNoteUser() {
        doNothing().when(noteRepo).deleteById(1L);
        assertThatThrownBy(() -> noteService.removeNote(1L)).isInstanceOf(AccessDeniedException.class);
    }
}

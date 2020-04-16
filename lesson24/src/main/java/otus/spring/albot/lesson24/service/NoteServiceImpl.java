package otus.spring.albot.lesson24.service;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson24.repo.NoteRepo;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private NoteRepo noteRepo;

    @Override
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public void removeNote(Long noteId) {
        noteRepo.deleteById(noteId);
    }
}

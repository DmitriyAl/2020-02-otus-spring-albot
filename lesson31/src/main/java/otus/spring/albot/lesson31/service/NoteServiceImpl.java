package otus.spring.albot.lesson31.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson31.repo.NoteRepo;

@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private NoteRepo noteRepo;

    @Transactional
    @Override
    public void removeNote(Long noteId) {
        noteRepo.deleteById(noteId);
    }
}

package otus.spring.albot.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.project.entity.Note;

public interface NoteRepo extends JpaRepository<Note, Long> {
}

package otus.spring.albot.lesson34.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson34.entity.Note;

public interface NoteRepo extends JpaRepository<Note, Long> {
}

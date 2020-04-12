package otus.spring.albot.lesson22.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson22.entity.Note;

public interface NoteRepo extends JpaRepository<Note, Long> {
}

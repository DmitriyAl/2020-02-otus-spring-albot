package otus.spring.albot.lesson11.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson11.dao.BookRepo;
import otus.spring.albot.lesson11.dao.NoteRepo;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Note;
import otus.spring.albot.lesson11.exception.NoSuchNoteException;

/**
 * <pre>
 * $Id: $
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * </pre>
 *
 * @author Dmitrii Albot
 */
@Service
@AllArgsConstructor
public class NoteServiceImpl implements NoteService {
    private BookRepo bookRepo;
    private NoteRepo noteRepo;

    @Override
    @Transactional
    public Note addNoteToBook(long bookId, String textNote) {
        Book book = bookRepo.findById(bookId).orElse(null);
        if (book == null) {
            return null;
        }
        return noteRepo.save(new Note(textNote, book));
    }

    @Override
    @Transactional
    public void removeNote(long id) throws NoSuchNoteException {
        Note note = noteRepo.findById(id).orElseThrow(() -> new NoSuchNoteException(id));
        noteRepo.delete(note);
    }
}
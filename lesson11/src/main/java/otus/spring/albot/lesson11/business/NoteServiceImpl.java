package otus.spring.albot.lesson11.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson11.dao.BookRepo;
import otus.spring.albot.lesson11.dao.NoteRepo;
import otus.spring.albot.lesson11.entity.Book;
import otus.spring.albot.lesson11.entity.Note;

import java.util.Optional;

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
    public Book addNoteToBook(long bookId, String textNote) {
        Optional<Book> bookOptional = bookRepo.findById(bookId);
        if (!bookOptional.isPresent()) {
            return null;
        }
        Book book = bookOptional.get();
        Note note = new Note();
        note.setNote(textNote);
        return bookRepo.saveAndFlush(book);
    }

    @Override
    @Transactional
    public void removeNote(long id) {
        noteRepo.deleteById(id);
    }
}
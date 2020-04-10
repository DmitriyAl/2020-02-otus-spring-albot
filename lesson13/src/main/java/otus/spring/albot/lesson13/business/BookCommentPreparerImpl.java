package otus.spring.albot.lesson13.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson13.dao.BookRepo;
import otus.spring.albot.lesson13.dao.NoteRepo;
import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.entity.Note;
import otus.spring.albot.lesson13.model.BookNotes;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookCommentPreparerImpl implements BookCommentPreparer {
    private BookRepo bookRepo;
    private NoteRepo noteRepo;

    @Override
    @Transactional
    public BookNotes extractAllNotesForBookById(String id) {
        Optional<Book> optional = bookRepo.findById(id);
        return getBookNotes(optional.orElse(null));
    }

    @Override
    @Transactional
    public BookNotes extractAllNotesForBookByName(String name) {
        Optional<Book> optional = bookRepo.findByName(name);
        return getBookNotes(optional.orElse(null));
    }

    private BookNotes getBookNotes(Book book) {
        if (book == null) {
            return null;
        }
        List<Note> notesForBook = noteRepo.findByBook(book);
        return fillInBookNotes(book, notesForBook);
    }

    private BookNotes fillInBookNotes(Book book, List<Note> notes) {
        return new BookNotes(book.getName(), notes);
    }
}

//package otus.spring.albot.lesson20.business;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import otus.spring.albot.lesson20.dao.BookRepo;
//import otus.spring.albot.lesson20.dao.NoteRepo;
//import otus.spring.albot.lesson20.entity.Book;
//import otus.spring.albot.lesson20.entity.Note;
//import otus.spring.albot.lesson20.exception.NoSuchNoteException;
//
//@Service
//@AllArgsConstructor
//public class NoteServiceImpl implements NoteService {
//    private BookRepo bookRepo;
//    private NoteRepo noteRepo;
//
//    @Override
//    @Transactional
//    public Note addNoteToBook(String bookId, String textNote) {
//        Book book = bookRepo.findById(bookId).orElse(null);
//        if (book == null) {
//            return null;
//        }
//        return noteRepo.save(new Note(textNote, book));
//    }
//
//    @Override
//    @Transactional
//    public void removeNote(String id) throws NoSuchNoteException {
//        Note note = noteRepo.findById(id).orElseThrow(() -> new NoSuchNoteException(id));
//        noteRepo.delete(note);
//    }
//}

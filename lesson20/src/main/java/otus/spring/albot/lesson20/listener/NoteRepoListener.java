//package otus.spring.albot.lesson20.listener;
//
//import lombok.AllArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
//import org.springframework.data.mongodb.core.mapping.event.AfterSaveEvent;
//import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
//import org.springframework.stereotype.Component;
//import otus.spring.albot.lesson20.dao.BookRepo;
//import otus.spring.albot.lesson20.dao.NoteRepo;
//import otus.spring.albot.lesson20.entity.Book;
//import otus.spring.albot.lesson20.entity.Note;
//
//import java.util.ArrayList;
//import java.util.stream.Collectors;
//
//@Component
//@AllArgsConstructor
//public class NoteRepoListener extends AbstractMongoEventListener<Note> {
//    private BookRepo bookRepo;
//    private NoteRepo noteRepo;
//
//    @Override
//    public void onAfterSave(AfterSaveEvent<Note> event) {
//        super.onAfterSave(event);
//        final Note note = event.getSource();
//        bookRepo.findById(note.getBook().getId()).ifPresent(b -> addNoteToBook(note, b));
//    }
//
//    private void addNoteToBook(Note note, Book book) {
//        if (book.getNotes() == null) {
//            book.setNotes(new ArrayList<>());
//        }
//        book.getNotes().add(note);
//        bookRepo.save(book);
//    }
//
//    @Override
//    public void onBeforeDelete(BeforeDeleteEvent<Note> event) {
//        super.onBeforeDelete(event);
//        String id = event.getSource().get("_id").toString();
//        noteRepo.findById(id).ifPresent(this::updateBookNotes);
//    }
//
//    private void updateBookNotes(Note note) {
//        bookRepo.findById(note.getBook().getId()).ifPresent(book -> {
//                    book.setNotes(book.getNotes().stream()
//                            .filter(n -> !note.getId().equals(n.getId())).collect(Collectors.toList()));
//                    bookRepo.save(book);
//                }
//        );
//    }
//}

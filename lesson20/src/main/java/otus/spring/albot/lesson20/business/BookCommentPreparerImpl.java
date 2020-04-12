//package otus.spring.albot.lesson20.business;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import otus.spring.albot.lesson20.dao.BookRepo;
//import otus.spring.albot.lesson20.dao.NoteRepo;
//import otus.spring.albot.lesson20.entity.Note;
//import otus.spring.albot.lesson20.exception.NoSuchBookException;
//import reactor.core.publisher.Flux;
//import reactor.core.publisher.Mono;
//
//@Service
//@AllArgsConstructor
//public class BookCommentPreparerImpl implements BookCommentPreparer {
//    private BookRepo bookRepo;
//    private NoteRepo noteRepo;
//
//    @Override
//    @Transactional
//    public Flux<Note> extractAllNotesForBookById(String id) {
//        return noteRepo.findByBook(bookRepo.findById(id)
//                .switchIfEmpty(Mono.error(new NoSuchBookException(String.format("No book with id: %s", id)))));
//    }
//
//    @Override
//    @Transactional
//    public Flux<Note> extractAllNotesForBookByName(String name) {
//        return noteRepo.findByBook(bookRepo.findByName(name)
//                .switchIfEmpty(Mono.error(new NoSuchBookException(String.format("No book with name: %s", name)))));
//    }
//
////    private Mono<BookNotes> getBookNotes(Mono<Book> book) {
////        Flux<Note> notesForBook = noteRepo.findByBook(book);
////        return fillInBookNotes(book, notesForBook);
////    }
////
////    private Mono<BookNotes> fillInBookNotes(Mono<Book> book, Flux<Note> notes) {
//////        return new BookNotes(book.getName(), notes);
////        return null;
////    }
//}

package otus.spring.albot.lesson13.business;

import otus.spring.albot.lesson13.model.BookNotes;

public interface BookCommentPreparer {
    BookNotes extractAllNotesForBookById(String id);

    BookNotes extractAllNotesForBookByName(String template);
}

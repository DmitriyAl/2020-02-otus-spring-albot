package otus.spring.albot.lesson13.mongock;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.client.MongoDatabase;
import org.springframework.data.mongodb.core.MongoTemplate;
import otus.spring.albot.lesson13.entity.Author;
import otus.spring.albot.lesson13.entity.Book;
import otus.spring.albot.lesson13.entity.Genre;
import otus.spring.albot.lesson13.entity.Note;

import java.util.Arrays;
import java.util.Collections;

@ChangeLog(order = "001")
public class DatabaseChangelog {
    private Author pushkin;
    private Author tolstoy;
    private Author gogol;

    private Genre satire;
    private Genre novel;
    private Genre fairytale;

    private Book deathSouls;
    private Book warAndPeace;
    private Book goldenFish;
    private Book onegin;

    private Note note1;
    private Note note2;
    private Note note3;
    private Note note4;
    private Note note5;

    @ChangeSet(order = "001", id = "dbDrop", author = "me", runAlways = true)
    public void dropDb(MongoDatabase database) {
        database.drop();
    }

    @ChangeSet(order = "002", id = "initAuthors", author = "me", runAlways = true)
    public void initAuthors(MongoTemplate template) {
        pushkin = template.save(new Author("Alexander Pushkin"));
        tolstoy = template.save(new Author("Leo Tolstoy"));
        gogol = template.save(new Author("Nikolai Gogol"));
    }

    @ChangeSet(order = "003", id = "initGenres", author = "me", runAlways = true)
    public void initGenres(MongoTemplate template) {
        satire = template.save(new Genre("Satire"));
        novel = template.save(new Genre("Novel"));
        fairytale = template.save(new Genre("Fairytale"));
    }

    @ChangeSet(order = "004", id = "initBooks", author = "me", runAlways = true)
    public void initBooks(MongoTemplate template) {
        deathSouls = template.save(new Book("Death souls", gogol, satire));
        warAndPeace = template.save(new Book("War and peace", tolstoy, novel));
        goldenFish = template.save(new Book("The story about the fisherman and the golden fish", pushkin, fairytale));
        onegin = template.save(new Book("Evgeniy Onegin", pushkin, novel));
    }

    @ChangeSet(order = "005", id = "initNotes", author = "me", runAlways = true)
    public void initNotes(MongoTemplate template) {
        note1 = template.save(new Note("Good book", deathSouls));
        note2 = template.save(new Note("Interesting book", warAndPeace));
        note3 = template.save(new Note("The best book I have ever read", goldenFish));
        note4 = template.save(new Note("Boring book", onegin));
        note5 = template.save(new Note("Do not waste your time", deathSouls));
    }

    @ChangeSet(order = "006", id = "addNotesToBooks", author = "me", runAlways = true)
    public void addNotesToBooks(MongoTemplate template) {
        deathSouls.setNotes(Arrays.asList(note1, note5));
        deathSouls = template.save(deathSouls);
        warAndPeace.setNotes(Collections.singletonList(note2));
        warAndPeace = template.save(warAndPeace);
        goldenFish.setNotes(Collections.singletonList(note3));
        goldenFish = template.save(goldenFish);
        onegin.setNotes(Collections.singletonList(note4));
        onegin = template.save(onegin);
    }

    @ChangeSet(order = "007", id = "addBooksToAuthors", author = "me", runAlways = true)
    public void addBooksToAuthors(MongoTemplate template) {
        pushkin.setBooks(Arrays.asList(goldenFish, onegin));
        pushkin = template.save(pushkin);
        gogol.setBooks(Collections.singletonList(deathSouls));
        gogol = template.save(gogol);
        tolstoy.setBooks(Collections.singletonList(warAndPeace));
        tolstoy = template.save(tolstoy);
    }

    @ChangeSet(order = "008", id = "addBooksToGenres", author = "me", runAlways = true)
    public void addBooksToGenres(MongoTemplate template) {
        satire.setBooks(Collections.singletonList(deathSouls));
        satire = template.save(satire);
        novel.setBooks(Arrays.asList(warAndPeace, onegin));
        novel = template.save(novel);
        fairytale.setBooks(Collections.singletonList(goldenFish));
        fairytale = template.save(fairytale);
    }
}

package otus.spring.albot.lesson25.shell;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import otus.spring.albot.lesson25.dao.AuthorRepo;
import otus.spring.albot.lesson25.dao.BookRepo;
import otus.spring.albot.lesson25.dao.GenreRepo;
import otus.spring.albot.lesson25.document.AuthorDoc;
import otus.spring.albot.lesson25.document.BookDoc;
import otus.spring.albot.lesson25.document.GenreDoc;

import java.util.List;

@ShellComponent
@AllArgsConstructor
public class Controller {
    private JobLauncher jobLauncher;
    private Job job;
    private AuthorRepo authorRepo;
    private BookRepo bookRepo;
    private GenreRepo genreRepo;

    @ShellMethod(value = "Start migration", key = {"start-migration", "sm"})
    public String startMigration() {
        try {
            jobLauncher.run(job, new JobParameters());
        } catch (Exception e) {
            return "Migration failed: " + e.getMessage();
        }
        return "Migration completed";
    }

    @ShellMethod(value = "Get all authors", key = {"get-all-authors", "gaa"})
    public List<AuthorDoc> getAllAuthors() {
        return authorRepo.findAll();
    }

    @ShellMethod(value = "Get all books", key = {"get-all-books", "gab"})
    public List<BookDoc> getAllBooks() {
        return bookRepo.findAll();
    }

    @ShellMethod(value = "Get all genres", key = {"get-all-genres", "gag"})
    public List<GenreDoc> getAllGenres() {
        return genreRepo.findAll();
    }
}

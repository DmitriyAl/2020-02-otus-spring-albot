package otus.spring.albot.lesson13.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson13.dao.AuthorRepo;
import otus.spring.albot.lesson13.entity.Author;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepo authorRepo;

    @Override
    public List<Author> findAllAuthors() {
        return authorRepo.findAll();
    }

    @Override
    public Author saveNewAuthor(String name) {
        return authorRepo.save(new Author(name));
    }

    @Override
    public Author findByName(String name) {
        return authorRepo.findByName(name).orElse(null);
    }

    @Override
    @Transactional
    public void removeAuthorById(String id) {
        authorRepo.deleteById(id);
    }

    @Override
    public Author changeAuthorName(String id, String newName) {
        Optional<Author> optional = authorRepo.findById(id);
        if (optional.isPresent()) {
            Author author = optional.get();
            author.setName(newName);
            return authorRepo.save(author);
        }
        return null;
    }
}

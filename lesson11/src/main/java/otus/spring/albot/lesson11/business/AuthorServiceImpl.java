package otus.spring.albot.lesson11.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import otus.spring.albot.lesson11.dao.AuthorRepo;
import otus.spring.albot.lesson11.entity.Author;

import java.util.List;
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
    public boolean removeAuthorById(long id) {
        try {
            authorRepo.deleteById(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public Author changeAuthorName(long id, String newName) {
        Optional<Author> optional = authorRepo.findById(id);
        if (optional.isPresent()) {
            Author author = optional.get();
            author.setName(newName);
            return authorRepo.save(author);
        }
        return null;
    }
}
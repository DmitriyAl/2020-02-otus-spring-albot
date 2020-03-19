package otus.spring.albot.lesson9.business;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import otus.spring.albot.lesson9.dao.AuthorDao;

@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;

    @Override
    public boolean removeAuthorById(long id) {
        try {
            authorDao.deleteAuthor(id);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

package otus.spring.albot.lesson7.repo;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.spring.albot.lesson7.entity.Book;
import otus.spring.albot.lesson7.repo.mapper.BookMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Repository
@AllArgsConstructor
public class BookRepoImpl implements BookRepo {
    private NamedParameterJdbcOperations jdbc;

    @Override
    public List<Book> findBookByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "%" + name + "%");
        return jdbc.query("SELECT b.id, b.name FROM books b WHERE b.name LIKE :name", params, new BookMapper());
    }

    @Override
    public List<Book> findAllBooks() {
        return jdbc.query("SELECT b.id, b.name FROM books b", new BookMapper());
    }

    @Override
    public int saveBook(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return jdbc.update("insert into books (name) values (:name)", params);
    }

    @Override
    public int deleteBook(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.update("delete from books where id=:id", params);
    }
}

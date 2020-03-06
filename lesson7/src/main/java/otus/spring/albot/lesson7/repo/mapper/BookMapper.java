package otus.spring.albot.lesson7.repo.mapper;

import org.springframework.jdbc.core.RowMapper;
import otus.spring.albot.lesson7.entity.Book;

import java.sql.ResultSet;
import java.sql.SQLException;

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
public class BookMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Book(rs.getLong("id"), rs.getString("name"));
    }
}

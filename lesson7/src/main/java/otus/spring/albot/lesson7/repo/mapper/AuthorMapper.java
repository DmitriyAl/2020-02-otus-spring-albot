package otus.spring.albot.lesson7.repo.mapper;

import org.springframework.jdbc.core.RowMapper;
import otus.spring.albot.lesson7.entity.Author;

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
public class AuthorMapper implements RowMapper<Author> {
    @Override
    public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Author(rs.getLong("id"), rs.getString("name"));
    }
}

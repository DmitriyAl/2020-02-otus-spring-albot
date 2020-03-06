package otus.spring.albot.lesson7.repo.mapper;

import org.springframework.jdbc.core.RowMapper;
import otus.spring.albot.lesson7.entity.Genre;

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
public class GenreMapper implements RowMapper<Genre> {

    @Override
    public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Genre(rs.getLong("id"), rs.getString("name"));
    }
}

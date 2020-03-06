package otus.spring.albot.lesson7.repo;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import otus.spring.albot.lesson7.entity.Genre;
import otus.spring.albot.lesson7.repo.mapper.GenreMapper;

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
public class GenreRepoImpl implements GenreRepo {
    private NamedParameterJdbcOperations jdbc;

    @Override
    public List<Genre> findAllGenresByName(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", "%" + name + "%");
        return jdbc.query("SELECT g.id, g.name FROM genres g WHERE g.name LIKE :name", params, new GenreMapper());
    }

    @Override
    public List<Genre> findAllGenres() {
        return jdbc.query("SELECT g.id, g.name FROM genres g", new GenreMapper());
    }

    @Override
    public int saveGenre(String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return jdbc.update("insert into genres (name) values (:name)", params);
    }

    @Override
    public int deleteGenre(long id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return jdbc.update("delete from genres where id=:id", params);
    }
}

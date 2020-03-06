package otus.spring.albot.lesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

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
@Data
@AllArgsConstructor
public class Author {
    private long id;
    private String name;
}

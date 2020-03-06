package otus.spring.albot.lesson5.util.question;

import otus.spring.albot.lesson5.model.ParsedLine;

import java.util.List;

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
public interface QuizParser {
    List<ParsedLine> parse();
}

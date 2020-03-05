package otus.spring.albot.lesson2.util.question;

import otus.spring.albot.lesson2.model.ParsedLine;

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
public interface QuestionsPreparer {
    List<ParsedLine> prepareQuestions(final List<ParsedLine> lines);
}

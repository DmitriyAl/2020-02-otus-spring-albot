package otus.spring.albot.util.question;

import otus.spring.albot.model.ParsedLine;

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
public interface QuestionLocalizer {
    void localizeQuestions(List<ParsedLine> parsedLines);
}

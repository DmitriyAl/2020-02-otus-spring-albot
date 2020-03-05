package otus.spring.albot.lesson2.questionHandler;

import otus.spring.albot.lesson2.exception.QuestionTypeIsNotSupportedException;
import otus.spring.albot.lesson2.model.QuestionType;

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
public interface QuestionHandlerFactory {
    QuestionHandler getHandler(QuestionType type) throws QuestionTypeIsNotSupportedException;
}

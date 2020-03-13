package otus.spring.albot.lesson5.util.question;

import org.springframework.stereotype.Service;
import otus.spring.albot.lesson5.model.ParsedLine;
import otus.spring.albot.lesson5.model.QuestionType;

import java.util.Collections;
import java.util.List;

/**
 * @author Dmitrii Albot
 */
@Service
public class QuestionsPreparerImpl implements QuestionsPreparer {
    public List<ParsedLine> prepareQuestions(final List<ParsedLine> lines) {
        if (lines == null || lines.isEmpty()) {
            throw new IllegalArgumentException("List of questions is empty! Please specify the questions!");
        }
        for (ParsedLine line : lines) {
            if (line.getType().equals(QuestionType.C) && line.getChoices() != null) {
                Collections.shuffle(line.getChoices());
            }
        }
        Collections.shuffle(lines);
        return lines;
    }
}

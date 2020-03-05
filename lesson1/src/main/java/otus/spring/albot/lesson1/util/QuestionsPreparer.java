package otus.spring.albot.lesson1.util;

import otus.spring.albot.lesson1.model.QuestionType;
import otus.spring.albot.lesson1.model.ParsedLine;

import java.util.Collections;
import java.util.List;

/**
 * @author Dmitrii Albot
 */
public class QuestionsPreparer {
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

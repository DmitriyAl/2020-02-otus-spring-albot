package otus.spring.albot.lesson2.game;

import otus.spring.albot.lesson2.model.ParsedLine;
import otus.spring.albot.lesson2.util.question.QuestionLocalizer;
import otus.spring.albot.lesson2.util.question.QuestionsPreparer;
import otus.spring.albot.lesson2.util.question.QuizParser;

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

public class GamePreparerImpl implements GamePreparer {
    private final QuizParser quizParser;
    private final QuestionsPreparer questionsPreparer;
    private final QuestionLocalizer questionLocalizer;

    public GamePreparerImpl(
            QuizParser quizParser, QuestionsPreparer questionsPreparer, QuestionLocalizer questionLocalizer) {
        this.quizParser = quizParser;
        this.questionsPreparer = questionsPreparer;
        this.questionLocalizer = questionLocalizer;
    }


    @Override
    public List<ParsedLine> prepareQuestions() {
        List<ParsedLine> lines = quizParser.parse();
        lines = questionsPreparer.prepareQuestions(lines);
        questionLocalizer.localizeQuestions(lines);
        return lines;
    }
}

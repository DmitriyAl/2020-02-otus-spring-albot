package otus.spring.albot.lesson2.questionHandler;

import otus.spring.albot.lesson2.exception.IncorrectAnswerException;
import otus.spring.albot.lesson2.model.ParsedLine;

/**
 * @author Dmitrii Albot
 */
public class TrueFalseQH extends QuestionHandler {

    @Override
    protected void addExtraPartForQuestion(ParsedLine question, StringBuilder sb) {

    }

    @Override
    public boolean handleQuestion(ParsedLine question, String answer) throws IncorrectAnswerException {
        Boolean correctAnswer = Boolean.valueOf(question.getAnswer());
        if (correctAnswer && matchCorrect(answer)) {
            return true;
        } else if (!correctAnswer && matchIncorrect(answer)) {
            return true;
        } else if (matchCorrect(answer) || matchIncorrect(answer)) {
            return false;
        } else {
            throw new IncorrectAnswerException(
                    "The input value is incorrect, please try again. Correct answers are \'+\', \'t\', \'y\', "
                            + "\'true\' for the positive answer and \'-\', \'f\', \'n\', \'false\' for the negative "
                            + "one");
        }
    }

    private boolean matchCorrect(String answer) {
        return answer.equals("+") || answer.equals("t") || answer.equals("y") || answer.equals("true");
    }

    private boolean matchIncorrect(String answer) {
        return answer.equals("-") || answer.equals("f") || answer.equals("n") || answer.equals("false");
    }
}

package otus.spring.albot.questionHandler;

import otus.spring.albot.exception.IncorrectAnswerException;
import otus.spring.albot.model.ParsedLine;

/**
 * @author Dmitrii Albot
 */
public abstract class QuestionHandler {

    public void askQuestion(int questionNumber, ParsedLine question) {
        StringBuilder sb = new StringBuilder();
        sb.append(questionNumber);
        sb.append(") ");
        sb.append(question.getQuestion());
        addExtraPartForQuestion(question, sb);
        System.out.println(sb);
    }

    protected abstract void addExtraPartForQuestion(ParsedLine question, StringBuilder sb);

    public boolean handleQuestion(ParsedLine question, String answer) throws IncorrectAnswerException {
        if (question.getAnswer().toLowerCase().equals(answer.toLowerCase())) {
            return true;
        }
        return false;
    }
}

package otus.spring.albot.lesson1.handler;

import otus.spring.albot.lesson1.exception.IncorrectAnswerException;
import otus.spring.albot.lesson1.model.ParsedLine;

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

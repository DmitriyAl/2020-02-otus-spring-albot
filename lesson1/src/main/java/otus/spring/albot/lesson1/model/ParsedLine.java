package otus.spring.albot.lesson1.model;

import java.util.List;

/**
 * @author Dmitrii Albot
 */
public class ParsedLine {
    private QuestionType type;
    private String question;
    private String answer;
    private List<String> choices;

    public ParsedLine(QuestionType type, String question, String answer) {
        this.type = type;
        this.question = question;
        this.answer = answer;
    }

    public ParsedLine(QuestionType type, String question, String answer, List<String> choices) {
        this.type = type;
        this.question = question;
        this.answer = answer;
        this.choices = choices;
    }

    public QuestionType getType() {
        return type;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public List<String> getChoices() {
        return choices;
    }
}

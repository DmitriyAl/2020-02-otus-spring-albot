package otus.spring.albot.lesson1.handler;

import otus.spring.albot.lesson1.exception.IncorrectAnswerException;
import otus.spring.albot.lesson1.model.ParsedLine;

import java.util.stream.Collectors;

/**
 * @author Dmitrii Albot
 */
public class ChoiceQH extends QuestionHandler {
    private char firstChoiceChar = 97;
    private String errorMessage =
            "The answer format is incorrect! The answer should be whether the letter with \')\' symbol of the " +
                    "value of the choice";

    @Override
    protected void addExtraPartForQuestion(ParsedLine question, StringBuilder sb) {
        char currentChar = firstChoiceChar;
        for (String choiceValue : question.getChoices()) {
            sb.append("\n");
            sb.append(currentChar++);
            sb.append(")");
            sb.append(choiceValue);
        }
    }

    @Override
    public boolean handleQuestion(ParsedLine question, String answer) throws IncorrectAnswerException {
        if (!answer.contains(")")) {
            return handleValueFormatAnswer(question, answer);
        } else {
            validateAnswer(answer);
            return handleChoiceFormatAnswer(question, answer.charAt(0));
        }
    }

    private boolean handleValueFormatAnswer(ParsedLine question, String answer) throws IncorrectAnswerException {
        if (!question.getChoices().stream().map(String::toLowerCase).collect(Collectors.toList())
                .contains(answer.toLowerCase())) {
            throw new IncorrectAnswerException(errorMessage);
        } else {
            return super.handleQuestion(question, answer);
        }
    }

    private void validateAnswer(String answer) throws IncorrectAnswerException {
        if (answer.charAt(1) != ')') {
            throw new IncorrectAnswerException(errorMessage);
        }
    }

    private boolean handleChoiceFormatAnswer(ParsedLine question, char answer) throws IncorrectAnswerException {
        int index = answer - 97;
        if (index < 0) {
            throw new IncorrectAnswerException(errorMessage);
        }
        try {
            String value = question.getChoices().get(index);
            return value.toLowerCase().equals(question.getAnswer());
        } catch (IndexOutOfBoundsException ex) {
            throw new IncorrectAnswerException(errorMessage);
        }
    }
}

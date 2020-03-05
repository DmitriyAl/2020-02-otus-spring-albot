package otus.spring.albot.util.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import otus.spring.albot.model.ParsedLine;
import otus.spring.albot.util.message.MessageHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Dmitrii Albot
 */
@Service
public class QuestionLocalizerImpl implements QuestionLocalizer {
    private MessageHandler messageHandler;
    private String open;
    private String close;

    @Autowired
    public QuestionLocalizerImpl(
            MessageHandler messageHandler, @Value("${pattern.open}") String open,
            @Value("${pattern.close}") String close) {
        this.messageHandler = messageHandler;
        this.open = open;
        this.close = close;
    }

    public void localizeQuestions(List<ParsedLine> parsedLines) {
        for (ParsedLine parsedLine : parsedLines) {
            String answer = parsedLine.getAnswer();
            if (isKey(answer)) {
                parsedLine.setAnswer(localize(answer));
            }
            String question = parsedLine.getQuestion();
            if (isKey(question)) {
                parsedLine.setQuestion(localize(question));
            }
            List<String> choices = parsedLine.getChoices();
            if (choices != null) {
                List<String> localizedChoices = new ArrayList<>();
                for (String choice : choices) {
                    if (isKey(choice)) {
                        choice = localize(choice);
                    }
                    localizedChoices.add(choice);
                }
                parsedLine.setChoices(localizedChoices);
            }
        }
    }

    private boolean isKey(String word) {
        return word.contains(open) && word.contains(close);
    }

    private String localize(String key) {
        key = key.substring(open.length(), key.length() - close.length());
        return messageHandler.getMessage(key);
    }
}

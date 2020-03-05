package otus.spring.albot.lesson1.handler;

import org.apache.log4j.Logger;
import otus.spring.albot.lesson1.exception.QuestionTypeIsNotSupportedException;
import otus.spring.albot.lesson1.model.QuestionType;

/**
 * @author Dmitrii Albot
 */
public class QuestionHandlerFactory {
    private QuestionHandler choiceQH;
    private QuestionHandler multChoiceQH;
    private QuestionHandler trueFalseQH;
    private QuestionHandler commonQH;
    private static final Logger LOG = Logger.getLogger(QuestionHandlerFactory.class);

    public QuestionHandler getHandler(QuestionType type) throws QuestionTypeIsNotSupportedException {
        switch (type) {
            case C:
                return getChoiceQH();
            case MC:
                LOG.debug("The multi choice question type is not supported up for now");
                throw new QuestionTypeIsNotSupportedException("The question type is not supported");
            case TF:
                return getTrueFalseQH();
            case O:
                return getCommonQH();
            default:
                throw new QuestionTypeIsNotSupportedException("The question type is not supported");
        }
    }

    private QuestionHandler getChoiceQH() {
        if (choiceQH == null) {
            choiceQH = new ChoiceQH();
        }
        return choiceQH;
    }

    private QuestionHandler getMultChoiceQH() {
        if (multChoiceQH == null) {
            multChoiceQH = new MultipleChoiceQH();
        }
        return multChoiceQH;
    }

    private QuestionHandler getTrueFalseQH() {
        if (trueFalseQH == null) {
            trueFalseQH = new TrueFalseQH();
        }
        return trueFalseQH;
    }

    private QuestionHandler getCommonQH() {
        if (commonQH == null) {
            commonQH = new CommonQH();
        }
        return commonQH;
    }
}

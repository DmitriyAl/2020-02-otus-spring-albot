package otus.spring.albot.model;

/**
 * @author Dmitrii Albot
 */
public enum QuestionType {
    C("choice"), MC("multiple choice"), TF("true/false"), O("opened");

    private String description;

    QuestionType(String description) {
        this.description = description;
    }
}

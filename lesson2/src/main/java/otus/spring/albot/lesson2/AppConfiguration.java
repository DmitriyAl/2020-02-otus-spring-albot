package otus.spring.albot.lesson2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import otus.spring.albot.lesson2.game.GameManagerImpl;
import otus.spring.albot.lesson2.game.GameManager;
import otus.spring.albot.lesson2.game.GamePreparer;
import otus.spring.albot.lesson2.game.GamePreparerImpl;
import otus.spring.albot.lesson2.questionHandler.QuestionHandlerFactory;
import otus.spring.albot.lesson2.questionHandler.QuestionHandlerFactoryImpl;
import otus.spring.albot.lesson2.util.message.MessageHandler;
import otus.spring.albot.lesson2.util.message.MessageHandlerImpl;
import otus.spring.albot.lesson2.util.question.QuestionLocalizer;
import otus.spring.albot.lesson2.util.question.QuestionsPreparer;
import otus.spring.albot.lesson2.util.question.QuizParser;
import otus.spring.albot.lesson2.util.question.QuestionLocalizerImpl;
import otus.spring.albot.lesson2.util.question.QuestionsPreparerImpl;
import otus.spring.albot.lesson2.util.question.QuizParserImpl;

import java.util.Locale;

/**
 * @author Dmitrii Albot
 */

@Configuration
@PropertySource("classpath:app.properties")
@ComponentScan
public class AppConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public QuestionHandlerFactory questionHandlerFactory() {
        return new QuestionHandlerFactoryImpl();
    }

    @Bean
    public QuestionsPreparer questionsPreparer() {
        return new QuestionsPreparerImpl();
    }

    @Bean
    public QuizParser csvParser(@Value("${path.questions}") String path) {
        return new QuizParserImpl(path);
    }

    @Bean
    public GamePreparer gamePreparer(
            QuizParser quizParser, QuestionsPreparer questionsPreparer, QuestionLocalizer localizer) {
        return new GamePreparerImpl(quizParser, questionsPreparer, localizer);
    }

    @Bean
    public GameManager gameManager(
            GamePreparer gamePreparer, QuestionHandlerFactory factory, MessageHandler messageHandler,
            @Value("${game.greeting}") String greeting, @Value("${game.rules}") String rules,
            @Value("${game.amount}") int amount, @Value("${game.result}") String result) {
        return new GameManagerImpl(gamePreparer, factory, messageHandler, greeting, rules, result, amount);
    }

    @Bean
    public QuestionLocalizer getLocalizer(
            MessageHandler handler, @Value("${pattern.open}") String open, @Value("${pattern.close}") String close) {
        return new QuestionLocalizerImpl(handler, open, close);
    }

    @Bean
    public MessageSource messageSource(
            @Value("${path.localization}") String path, @Value("${encoding}") String encoding) {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename(path);
        source.setDefaultEncoding(encoding);
        return source;
    }

    @Bean
    public MessageHandler messageHandler(
            MessageSource messageSource, @Value("${localization.language}") String languageTag) {
        return new MessageHandlerImpl(messageSource, Locale.forLanguageTag(languageTag));
    }
}

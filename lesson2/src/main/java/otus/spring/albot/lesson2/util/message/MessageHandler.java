package otus.spring.albot.lesson2.util.message;

import org.springframework.context.MessageSource;

import java.util.Locale;

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
public interface MessageHandler {
    String getMessage(String key, String... params);

    void setLocale(Locale locale);
}
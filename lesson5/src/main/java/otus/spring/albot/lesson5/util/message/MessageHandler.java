package otus.spring.albot.lesson5.util.message;

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

    String getSelectedLanguage();
}
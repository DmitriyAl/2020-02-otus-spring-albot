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
public class MessageHandlerImpl implements MessageHandler {
    private MessageSource messageSource;
    private Locale locale;

    public MessageHandlerImpl(MessageSource messageSource, Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    public String getMessage(String key, String... params) {
        return messageSource.getMessage(key, params, locale);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}

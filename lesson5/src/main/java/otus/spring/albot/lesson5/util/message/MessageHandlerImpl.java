package otus.spring.albot.lesson5.util.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

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
@Service
public class MessageHandlerImpl implements MessageHandler {
    private MessageSource messageSource;
    private Locale locale;

    @Autowired
    public MessageHandlerImpl(MessageSource messageSource, @Value("${localization.language}") String languageTag) {
        this.messageSource = messageSource;
        this.locale = Locale.forLanguageTag(languageTag);
    }

    public String getMessage(String key, String... params) {
        return messageSource.getMessage(key, params, locale);
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public String getSelectedLanguage() {
        String[] params = {locale.getDisplayLanguage(), locale.getLanguage()};
        String selectedLangKey = "messageHandler.selectedLanguage";
        return messageSource.getMessage(selectedLangKey, params, locale);
    }
}

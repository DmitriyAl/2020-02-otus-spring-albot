package otus.spring.albot.lesson5.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import otus.spring.albot.lesson5.game.GameManager;
import otus.spring.albot.lesson5.util.message.MessageHandler;

import java.util.Locale;

@ShellComponent
@AllArgsConstructor
public class ShellController {
    private GameManager manager;
    private MessageHandler messages;

    @ShellMethod(value = "Start test", key = {"start", "s"})
    public void startTest() {
        manager.launch();
    }

    @ShellMethod(value = "Check language", key = {"getLang", "gl"})
    public String checkLang() {
        return messages.getSelectedLanguage();
    }

    @ShellMethod(value = "Set locale", key = {"setLocale", "sl"})
    public String setLocale(@ShellOption String locale) {
        messages.setLocale(Locale.forLanguageTag(locale));
        return messages.getSelectedLanguage();
    }
}

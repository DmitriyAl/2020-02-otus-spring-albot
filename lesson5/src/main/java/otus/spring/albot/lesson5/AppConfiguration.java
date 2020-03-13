package otus.spring.albot.lesson5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

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
@Configuration
public class AppConfiguration {
    @Bean
    public MessageSource messageSource(
            @Value("${path.localization}") String path, @Value("${encoding}") String encoding) {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasename(path);
        source.setDefaultEncoding(encoding);
        return source;
    }
}

package otus.spring.albot.lesson25.batch;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfig {
    public JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job importUserJob(@Qualifier("author") Step authorStep,
                             @Qualifier("book") Step bookStep,
                             @Qualifier("genre") Step genreStep,
                             @Qualifier("note") Step noteStep) {
        return jobBuilderFactory.get("migrate")
                .start(authorStep)
                .next(genreStep)
                .next(bookStep)
                .next(noteStep)
                .build();
    }
}

package otus.spring.albot.lesson25.batch.step;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otus.spring.albot.lesson25.dao.AuthorRepo;
import otus.spring.albot.lesson25.document.AuthorDoc;
import otus.spring.albot.lesson25.entity.AuthorJpa;

import javax.persistence.EntityManagerFactory;

@Configuration
@AllArgsConstructor
public class AuthorStepConfiguration {
    private EntityManagerFactory entityManagerFactory;
    private StepBuilderFactory stepBuilderFactory;
    private AuthorRepo authorRepo;

    @Bean
    @Qualifier("author")
    public Step authorsStep() {
        return stepBuilderFactory.get("authorsStep")
                .<AuthorJpa, AuthorDoc>chunk(5)
                .reader(authorReader())
                .processor(authorProcessor())
                .writer(authorWriter())
                .build();
    }

    private ItemReader<AuthorJpa> authorReader() {
        return new JpaPagingItemReaderBuilder<AuthorJpa>()
                .name("authorReader")
                .queryString("select a from AuthorJpa a")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(1000)
                .build();
    }

    private ItemProcessor<AuthorJpa, AuthorDoc> authorProcessor() {
        return AuthorDoc::fromJpa;
    }

    private ItemWriter<AuthorDoc> authorWriter() {
        return items -> authorRepo.saveAll(items);
    }
}

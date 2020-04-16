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
import otus.spring.albot.lesson25.dao.GenreRepo;
import otus.spring.albot.lesson25.document.GenreDoc;
import otus.spring.albot.lesson25.entity.GenreJpa;

import javax.persistence.EntityManagerFactory;

@Configuration
@AllArgsConstructor
public class GenreStepConfiguration {
    private EntityManagerFactory entityManagerFactory;
    private StepBuilderFactory stepBuilderFactory;
    private GenreRepo genreRepo;

    @Bean
    @Qualifier("genre")
    public Step genresStep() {
        return stepBuilderFactory.get("genresStep")
                .<GenreJpa, GenreDoc>chunk(5)
                .reader(genreReader())
                .processor(genreProcessor())
                .writer(genreWriter())
                .build();
    }

    private ItemReader<GenreJpa> genreReader() {
        return new JpaPagingItemReaderBuilder<GenreJpa>()
                .name("genreReader")
                .queryString("select g from GenreJpa g")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(1000)
                .build();
    }


    private ItemProcessor<GenreJpa, GenreDoc> genreProcessor() {
        return GenreDoc::fromJpa;
    }


    private ItemWriter<GenreDoc> genreWriter() {
        return items -> genreRepo.saveAll(items);
    }
}

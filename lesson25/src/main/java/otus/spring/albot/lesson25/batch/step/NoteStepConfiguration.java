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
import otus.spring.albot.lesson25.dao.NoteRepo;
import otus.spring.albot.lesson25.document.NoteDoc;
import otus.spring.albot.lesson25.entity.NoteJpa;

import javax.persistence.EntityManagerFactory;

@Configuration
@AllArgsConstructor
public class NoteStepConfiguration {
    private EntityManagerFactory entityManagerFactory;
    private StepBuilderFactory stepBuilderFactory;
    private NoteRepo noteRepo;

    @Bean
    @Qualifier("note")
    public Step notesStep() {
        return stepBuilderFactory.get("notesStep")
                .<NoteJpa, NoteDoc>chunk(5)
                .reader(noteReader())
                .processor(noteProcessor())
                .writer(noteWriter())
                .build();
    }

    private ItemReader<NoteJpa> noteReader() {
        return new JpaPagingItemReaderBuilder<NoteJpa>()
                .name("noteReader")
                .queryString("select n from NoteJpa n")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(1000)
                .build();
    }


    private ItemProcessor<NoteJpa, NoteDoc> noteProcessor() {
        return NoteDoc::fromJpa;
    }


    private ItemWriter<NoteDoc> noteWriter() {
        return items -> noteRepo.saveAll(items);
    }
}

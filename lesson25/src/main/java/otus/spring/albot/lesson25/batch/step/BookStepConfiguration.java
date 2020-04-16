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
import otus.spring.albot.lesson25.dao.BookRepo;
import otus.spring.albot.lesson25.document.BookDoc;
import otus.spring.albot.lesson25.entity.BookJpa;

import javax.persistence.EntityManagerFactory;

@Configuration
@AllArgsConstructor
public class BookStepConfiguration {
    private EntityManagerFactory entityManagerFactory;
    private StepBuilderFactory stepBuilderFactory;
    private BookRepo bookRepo;

    @Bean
    @Qualifier("book")
    public Step booksStep() {
        return stepBuilderFactory.get("booksStep")
                .<BookJpa, BookDoc>chunk(5)
                .reader(bookReader())
                .processor(bookProcessor())
                .writer(bookWriter())
                .build();
    }

    private ItemReader<BookJpa> bookReader() {
        return new JpaPagingItemReaderBuilder<BookJpa>()
                .name("bookReader")
                .queryString("select b from BookJpa b")
                .entityManagerFactory(entityManagerFactory)
                .pageSize(1000)
                .build();
    }


    private ItemProcessor<BookJpa, BookDoc> bookProcessor() {
        return BookDoc::fromJpa;
    }


    private ItemWriter<BookDoc> bookWriter() {
        return items -> bookRepo.saveAll(items);
    }
}

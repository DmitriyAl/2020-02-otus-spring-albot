package otus.spring.albot.lesson20.mongock;

import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MongoBeeConfig {

    @Bean
    public Mongock mongock(MongoProperties properties, MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, properties.getDatabase(), DatabaseChangelog.class.getPackage()
                .getName()).build();
    }
}

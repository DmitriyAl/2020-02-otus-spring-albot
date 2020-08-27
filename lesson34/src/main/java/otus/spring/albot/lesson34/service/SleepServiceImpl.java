package otus.spring.albot.lesson34.service;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SleepServiceImpl implements SleepService {
    @Value("${app.waitThreshold:5000}")
    private int waitThreshold;

    @Override
    public void sleep() {
        int ms = new Random().nextInt(waitThreshold);
        try {
            TimeUnit.MILLISECONDS.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("It's just example, won't handle this correctly");
        }
    }
}

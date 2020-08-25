package otus.spring.albot.lesson34.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import otus.spring.albot.lesson34.service.SleepService;

@Controller
@AllArgsConstructor
public class AuthController {
    private final SleepService sleepService;

    @GetMapping(value = "login")
    @HystrixCommand
    public String login() {
        sleepService.sleep();
        return "login";
    }

    @GetMapping(value = "403")
    public String forbiddenPage() {
        return "error403";
    }
}

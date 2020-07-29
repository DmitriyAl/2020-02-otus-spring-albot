package otus.spring.albot.lesson29.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping(value = "login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "403")
    public String forbiddenPage() {
        return "error403";
    }
}

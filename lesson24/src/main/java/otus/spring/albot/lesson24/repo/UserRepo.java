package otus.spring.albot.lesson24.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson24.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}

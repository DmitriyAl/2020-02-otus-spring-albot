package otus.spring.albot.lesson34.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.lesson34.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}

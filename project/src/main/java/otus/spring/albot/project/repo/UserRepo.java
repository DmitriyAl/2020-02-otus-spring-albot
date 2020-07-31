package otus.spring.albot.project.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import otus.spring.albot.project.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
}
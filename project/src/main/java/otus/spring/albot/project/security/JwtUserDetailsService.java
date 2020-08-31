package otus.spring.albot.project.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import otus.spring.albot.project.entity.User;
import otus.spring.albot.project.exception.NoSuchUserException;
import otus.spring.albot.project.repo.UserRepo;
import otus.spring.albot.project.security.model.UserPrincipal;

@Service
@AllArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(s).orElseThrow(() -> new NoSuchUserException(s));
        return new UserPrincipal(user);
    }
}
package otus.spring.albot.lesson24.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import otus.spring.albot.lesson24.entity.User;
import otus.spring.albot.lesson24.exception.NoSuchUserException;
import otus.spring.albot.lesson24.model.UserPrincipal;
import otus.spring.albot.lesson24.repo.UserRepo;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(s).orElseThrow(() -> new NoSuchUserException(s));
        return new UserPrincipal(user);
    }
}

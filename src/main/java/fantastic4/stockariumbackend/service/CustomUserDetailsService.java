package fantastic4.stockariumbackend.service;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final fantastic4.stockariumbackend.repository.UserRepository userRepository;

    public CustomUserDetailsService(fantastic4.stockariumbackend.repository.UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        fantastic4.stockariumbackend.model.User user = userRepository.findByUsername(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found by their email"));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())      //le pasamos el email a spring security para q sea su "username"
                .password(user.getPassword())
                .roles("USER")
                .disabled(!user.isAdmin())
                .build();

    }
}

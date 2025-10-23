package fantastic4.stockariumbackend.service;

import fantastic4.stockariumbackend.DTOs.RegisterRequestDTO;
import fantastic4.stockariumbackend.model.User;
import fantastic4.stockariumbackend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterRequestDTO request){
        if (userRepository.existsByUsername(request.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())){
            throw new IllegalArgumentException("Email already exists");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        //hash de password
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setAdmin(request.isAdmin());
        return userRepository.save(user);
    }
}

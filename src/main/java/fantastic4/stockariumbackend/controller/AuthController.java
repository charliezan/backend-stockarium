package fantastic4.stockariumbackend.controller;

import fantastic4.stockariumbackend.DTOs.AuthResponseDTO;
import fantastic4.stockariumbackend.DTOs.LoginRequestDTO;
import fantastic4.stockariumbackend.DTOs.RegisterRequestDTO;
import fantastic4.stockariumbackend.Utils.JwtUtils;
import fantastic4.stockariumbackend.model.*;
import fantastic4.stockariumbackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            User user = authService.register(registerRequestDTO);
            return ResponseEntity.ok("User registered correctly: " + user.getUsername());
        } catch(IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        try{
            var authToken = new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
            authenticationManager.authenticate(authToken);
            String jwt = jwtUtils.generateToken(loginRequestDTO.getUsername());
            return ResponseEntity.ok(new AuthResponseDTO(jwt));
        } catch (AuthenticationException e) {
            return  ResponseEntity.status(401).body("Credenciales Invalidas");
        }
    }
    //Ejemplo de Endpoint protegido
    @GetMapping("/me")
    public ResponseEntity<?> me(@org.springframework.security.core.annotation.AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(401).body("User not found");
        }
        return ResponseEntity.ok("Hola, " + userDetails.getUsername());
    }
}

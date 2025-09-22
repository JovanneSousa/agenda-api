package br.com.jsousa.agenda.auth.controllers;

import br.com.jsousa.agenda.auth.dto.LoginRequestDTO;
import br.com.jsousa.agenda.auth.dto.RegisterRequestDTO;
import br.com.jsousa.agenda.auth.dto.ResponseAuthDTO;
import br.com.jsousa.agenda.auth.infra.security.TokenService;
import br.com.jsousa.agenda.domain.user.User;
import br.com.jsousa.agenda.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * AuthController.java
 *
 * Descrição da classe: Controller de autenticação
 *
 * @author jovan
 * @version 1.0
 * @since 9/21/2025
 */
@RestController
@RequestMapping(path = "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping(path = "/login")
    public ResponseEntity<ResponseAuthDTO> login(@RequestBody LoginRequestDTO body) {
        User user = this.userRepository.findByUsername(body.username())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseAuthDTO(user.getUsername(), token));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseAuthDTO> register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.userRepository.findByUsername(body.username());

        if (user.isEmpty()) {
            User newUser = new User();
            newUser.setName(body.name());
            newUser.setEmail(body.email());
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setUsername(body.username());

            this.userRepository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new ResponseAuthDTO(newUser.getUsername(), token));
        }

        return ResponseEntity.badRequest().build();
    }
}

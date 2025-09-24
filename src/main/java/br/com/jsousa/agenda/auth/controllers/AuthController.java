package br.com.jsousa.agenda.auth.controllers;

import br.com.jsousa.agenda.auth.dto.LoginRequestDTO;
import br.com.jsousa.agenda.auth.dto.RegisterRequestDTO;
import br.com.jsousa.agenda.auth.dto.ResponseAuthDTO;
import br.com.jsousa.agenda.auth.infra.security.TokenService;
import br.com.jsousa.agenda.auth.services.AuthService;
import br.com.jsousa.agenda.user.domain.User;
import br.com.jsousa.agenda.user.repositories.IUserRepository;
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

    private final AuthService authService;

    @PostMapping(path = "/login")
    public ResponseEntity<ResponseAuthDTO> login(@RequestBody LoginRequestDTO body) {
        return ResponseEntity.ok(authService.login(body));
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ResponseAuthDTO> register(@RequestBody RegisterRequestDTO body) {
        return ResponseEntity.ok(authService.register(body));
    }
}

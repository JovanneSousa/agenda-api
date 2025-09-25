package br.com.jsousa.agenda.auth.services;

import br.com.jsousa.agenda.auth.dto.LoginRequestDTO;
import br.com.jsousa.agenda.auth.dto.RegisterRequestDTO;
import br.com.jsousa.agenda.auth.dto.ResponseAuthDTO;
import br.com.jsousa.agenda.auth.infra.security.TokenService;
import br.com.jsousa.agenda.user.domain.User;
import br.com.jsousa.agenda.user.repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    public ResponseAuthDTO login(LoginRequestDTO body) {
        User user = userRepository.findByUsername(body.username())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        if (!passwordEncoder.matches(body.password(), user.getPassword())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = tokenService.generateToken(user);
        return new ResponseAuthDTO(user.getUsername(), user.getName(), token);
    }

    public ResponseAuthDTO register(RegisterRequestDTO body) {
        userRepository.findByUsername(body.username())
                .ifPresent(u -> { throw new RuntimeException("Usuário já existe!"); });

        User newUser = new User();
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(passwordEncoder.encode(body.password()));
        newUser.setUsername(body.username());

        userRepository.save(newUser);

        String token = tokenService.generateToken(newUser);
        return new ResponseAuthDTO(newUser.getUsername(), newUser.getName(), token);
    }
}

package com.najrotta.lingua.auth.application;

import com.najrotta.lingua.auth.domain.User;
import com.najrotta.lingua.common.security.JwtTokenProvider;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class AuthServiceImpl implements AuthService {

    private final Map<String, User> usersByEmail = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1L);

    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void register(RegisterRequest request) {
        String email = request.getEmail().toLowerCase();

        if (usersByEmail.containsKey(email)) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Long id = idSequence.getAndIncrement();

        User user = new User(
                id,
                request.getName(),
                email,
                request.getPassword() // en un futuro: password hasheado
        );

        usersByEmail.put(email, user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        String email = request.getEmail().toLowerCase();

        User user = usersByEmail.get(email);
        if (user == null || !user.getPassword().equals(request.getPassword())) {
            throw new IllegalArgumentException("Usuario o contraseña incorrectos");
        }

        String token = jwtTokenProvider.generateToken(
                user.getEmail(),
                user.getName(),
                user.getId()
        );

        return new LoginResponse(user.getName(), token);
    }
}

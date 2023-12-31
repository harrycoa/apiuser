package dev.rhc.apiuser.service;

import dev.rhc.apiuser.config.JwtUtil;
import dev.rhc.apiuser.exception.InvalidDataException;
import dev.rhc.apiuser.exception.UserAlreadyExistsException;
import dev.rhc.apiuser.exception.UserNotFoundException;
import dev.rhc.apiuser.model.User;
import dev.rhc.apiuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Value("${user.email.regex}")
    private String emailRegex;

    @Value("${user.password.regex}")
    private String passwordRegex;

    private final UserRepository userRepository;

    private final JwtUtil jwtUtil;


    public UserService(UserRepository userRepository, JwtUtil jwtUtil) {

        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User createUser(User user) throws UserAlreadyExistsException, InvalidDataException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("El correo ya registrado");
        }


        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(jwtUtil.generateToken(user));
        user.setActive(true);
        user.setPhones(user.getPhones());

        return userRepository.save(user);
    }

    private void validateEmail(String email) throws InvalidDataException {
        if (!email.matches(emailRegex)) {
            throw new InvalidDataException("Formato de correo inválido");
        }
    }

    private void validatePassword(String password) throws InvalidDataException {
        if (!password.matches(passwordRegex)) {
            throw new InvalidDataException("La contraseña debe tener al menos 8 caracteres, incluyendo una letra mayúscula, un número y un carácter especial");
        }
    }


}

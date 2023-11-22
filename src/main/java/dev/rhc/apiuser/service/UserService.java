package dev.rhc.apiuser.service;

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

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }


    public User createUser(User user) throws UserAlreadyExistsException, InvalidDataException {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException("El correo ya registrado");
        }

        validateEmail(user.getEmail());
        validatePassword(user.getPassword());

        // Configura los campos adicionales como created, modified, lastLogin, etc.
        user.setCreated(LocalDateTime.now());
        user.setModified(LocalDateTime.now());
        user.setLastLogin(LocalDateTime.now());
        user.setToken(UUID.randomUUID().toString());
        user.setActive(true);

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

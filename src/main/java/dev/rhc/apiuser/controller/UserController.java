package dev.rhc.apiuser.controller;

import dev.rhc.apiuser.dto.UserDto;
import dev.rhc.apiuser.exception.InvalidDataException;
import dev.rhc.apiuser.exception.UserAlreadyExistsException;
import dev.rhc.apiuser.model.User;
import dev.rhc.apiuser.service.UserService;
import dev.rhc.apiuser.util.UserDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {

    @Autowired
    private UserDtoConverter userDtoConverter;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        return new ResponseEntity<>(userDtoConverter.convertUserToDto(user), org.springframework.http.HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        try {
            User user = userDtoConverter.convertUserToEntity(userDto);
            User savedUser = userService.createUser(user);
            UserDto responseDto = userDtoConverter.convertUserToDto(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El correo ya registrado");
        } catch (InvalidDataException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    /*public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        User newUser = userDtoConverter.convertUserToEntity(userDto);
        newUser = userService.save(newUser);

        return new ResponseEntity<>(userDtoConverter.convertUserToDto(newUser), HttpStatus.CREATED);
    }*/




}

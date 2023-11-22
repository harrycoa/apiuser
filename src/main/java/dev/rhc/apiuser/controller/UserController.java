package dev.rhc.apiuser.controller;

import dev.rhc.apiuser.dto.ApiResponse;
import dev.rhc.apiuser.dto.UserDto;
import dev.rhc.apiuser.dto.UserResponseDto;
import dev.rhc.apiuser.exception.InvalidDataException;
import dev.rhc.apiuser.exception.UserAlreadyExistsException;
import dev.rhc.apiuser.model.User;
import dev.rhc.apiuser.service.UserService;
import dev.rhc.apiuser.util.UserDtoConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class UserController {

    private final UserDtoConverter userDtoConverter;

    private final UserService userService;

    public UserController(UserDtoConverter userDtoConverter, UserService userService) {
        this.userDtoConverter = userDtoConverter;
        this.userService = userService;
    }

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
            UserResponseDto responseDto = userDtoConverter.convertUserToResponseDto(savedUser);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);

        } catch (UserAlreadyExistsException e) {

            ApiResponse apiResponse = new ApiResponse("El correo ya fue registrado");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        } catch (InvalidDataException e) {
            ApiResponse apiResponse = new ApiResponse(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }



    }





}

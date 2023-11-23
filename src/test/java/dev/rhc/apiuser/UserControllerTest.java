package dev.rhc.apiuser;

import dev.rhc.apiuser.controller.UserController;
import dev.rhc.apiuser.dto.ApiResponse;
import dev.rhc.apiuser.dto.UserDto;
import dev.rhc.apiuser.dto.UserResponseDto;
import dev.rhc.apiuser.exception.UserAlreadyExistsException;
import dev.rhc.apiuser.model.User;
import dev.rhc.apiuser.service.UserService;
import dev.rhc.apiuser.util.UserDtoConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserControllerTest {
    @Mock
    private UserService userService;

    @Mock
    private UserDtoConverter userDtoConverter;

    @InjectMocks
    private UserController userController;

    @Test
    void findById() {
        UUID id = UUID.randomUUID();
        User user = new User();
        UserDto userDto = new UserDto();

        when(userService.findById(id)).thenReturn(user);
        when(userDtoConverter.convertUserToDto(user)).thenReturn(userDto);

        ResponseEntity<UserDto> response = userController.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userDto, response.getBody());
        verify(userService).findById(id);
        verify(userDtoConverter).convertUserToDto(user);
    }

    @Test
    void createUser_WhenUserCreated() {
        UserDto userDto = new UserDto();
        User user = new User();
        UserResponseDto responseDto = new UserResponseDto();

        when(userDtoConverter.convertUserToEntity(userDto)).thenReturn(user);
        when(userService.createUser(user)).thenReturn(user);
        when(userDtoConverter.convertUserToResponseDto(user)).thenReturn(responseDto);

        ResponseEntity<?> response = userController.createUser(userDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(responseDto, response.getBody());
        verify(userDtoConverter).convertUserToEntity(userDto);
        verify(userService).createUser(user);
        verify(userDtoConverter).convertUserToResponseDto(user);
    }

    @Test
    void createUser_WhenUserAlreadyExists() {
        UserDto userDto = new UserDto();
        when(userDtoConverter.convertUserToEntity(userDto)).thenReturn(new User());
        when(userService.createUser(any(User.class))).thenThrow(new UserAlreadyExistsException("El correo ya fue registrado"));

        ResponseEntity<?> response = userController.createUser(userDto);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("El correo ya fue registrado", ((ApiResponse) Objects.requireNonNull(response.getBody())).getMensaje());
    }

}

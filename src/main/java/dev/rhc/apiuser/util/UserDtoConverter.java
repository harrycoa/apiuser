package dev.rhc.apiuser.util;

import dev.rhc.apiuser.dto.UserDto;
import dev.rhc.apiuser.dto.UserResponseDto;
import dev.rhc.apiuser.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class UserDtoConverter {
    private final ModelMapper modelMapper;

    public UserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convertEntityToDtos(List<User> users) {
        List<UserDto> list = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = convertUserToDto(user);
            list.add(userDto);
        }
        return list;
    }

    public User convertUserToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public User map(User userToUpdate, User user) {
        modelMapper.map(userToUpdate, user);
        return user;
    }

    public UserResponseDto convertUserToResponseDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setCreated(user.getCreated());
        dto.setModified(user.getModified());
        dto.setLastLogin(user.getLastLogin());
        dto.setToken(user.getToken());
        dto.setActive(user.isActive());
        return dto;
    }
}
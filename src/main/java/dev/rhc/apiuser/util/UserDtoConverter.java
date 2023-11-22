package dev.rhc.apiuser.util;

import dev.rhc.apiuser.dto.UserDto;
import dev.rhc.apiuser.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convertEntityToDtos(List<User> users){
        return users.stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    public User convertUserToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    public User map(User userToUpdate, User user) {
        modelMapper.map(userToUpdate, user);
        return user;
    }
}

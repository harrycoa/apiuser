package dev.rhc.apiuser.util;

import dev.rhc.apiuser.dto.PhoneDto;
import dev.rhc.apiuser.dto.UserDto;
import dev.rhc.apiuser.dto.UserResponseDto;
import dev.rhc.apiuser.model.Phone;
import dev.rhc.apiuser.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class UserDtoConverter {
    private final ModelMapper modelMapper;

    public UserDtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public User convertUserToEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        if (userDto.getPhones() != null && !userDto.getPhones().isEmpty()) {
            user.setPhones(userDto.getPhones().stream().map(phoneDto -> {
                Phone phone = modelMapper.map(phoneDto, Phone.class);
                phone.setUser(user); // Establece la relación
                return phone;
            }).collect(Collectors.toSet()));
        }

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

    public UserDto convertUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setToken(user.getToken());
        userDto.setActive(user.isActive());
        userDto.setPhones(convertPhonesToDto(user.getPhones()));
        return userDto;
    }

    private List<PhoneDto> convertPhonesToDto(Set<Phone> phones) {
        if (phones == null) {
            return Collections.emptyList();
        }
        List<PhoneDto> list = new ArrayList<>();
        for (Phone phone : phones) {
            PhoneDto phoneDto = convertPhoneToDto(phone);
            list.add(phoneDto);
        }
        return list;
    }

    private PhoneDto convertPhoneToDto(Phone phone) {
        PhoneDto phoneDto = new PhoneDto();
        phoneDto.setId(phone.getId());
        phoneDto.setNumber(phone.getNumber());
        phoneDto.setCitycode(phone.getCitycode());
        phoneDto.setCountrycode(phone.getCountrycode());
        return phoneDto;
    }
}
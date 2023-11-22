package dev.rhc.apiuser.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String token;
    private boolean isActive;
    private List<PhoneDto> phones;
}

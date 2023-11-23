package dev.rhc.apiuser.dto;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String token;
    private boolean isActive;
    private List<PhoneDto> phones;
}

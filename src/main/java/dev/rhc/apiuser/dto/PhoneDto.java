package dev.rhc.apiuser.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {
    private UUID id;
    private String number;
    private String citycode;
    private String countrycode;
}

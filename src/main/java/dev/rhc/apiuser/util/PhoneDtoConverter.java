package dev.rhc.apiuser.util;

import dev.rhc.apiuser.dto.PhoneDto;
import dev.rhc.apiuser.model.Phone;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PhoneDtoConverter {
    @Autowired
    private ModelMapper modelMapper;

    public PhoneDto convertPhoneToDto(Phone phone) {
        return modelMapper.map(phone, PhoneDto.class);
    }

    public List<PhoneDto> convertEntityToDtos(List<Phone> phones){
        return phones.stream()
                .map(this::convertPhoneToDto)
                .collect(Collectors.toList());
    }

    public Phone convertPhoneToEntity(PhoneDto phoneDto) {
        return modelMapper.map(phoneDto, Phone.class);
    }

    public Phone map(Phone phoneToUpdate, Phone phone) {
        modelMapper.map(phoneToUpdate, phone);
        return phone;
    }

}

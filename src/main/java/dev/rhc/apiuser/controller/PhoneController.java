package dev.rhc.apiuser.controller;

import dev.rhc.apiuser.dto.PhoneDto;
import dev.rhc.apiuser.model.Phone;
import dev.rhc.apiuser.service.PhoneService;
import dev.rhc.apiuser.util.PhoneDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class PhoneController {

    @Autowired
    private PhoneDtoConverter phoneDtoConverter;

    @Autowired
    private PhoneService phoneService;

    @GetMapping("/phone/{id}")
    public ResponseEntity<PhoneDto> findById(@PathVariable("id") Long id) {
        Phone phone = phoneService.findById(id);
        return new ResponseEntity<>(phoneDtoConverter.convertPhoneToDto(phone), org.springframework.http.HttpStatus.OK);
    }
}

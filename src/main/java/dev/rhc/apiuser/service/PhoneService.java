package dev.rhc.apiuser.service;

import dev.rhc.apiuser.model.Phone;
import dev.rhc.apiuser.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Phone findById(Long id) {
        return phoneRepository.findById(id).orElse(null);
    }

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    public void delete(Phone phone) {
        phoneRepository.delete(phone);
    }
}

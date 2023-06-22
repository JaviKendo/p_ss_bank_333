package com.bank.profile.service;

import com.bank.profile.entity.Registration;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RegistrationServiceImpl implements RegistrationService {

    private final RegistrationRepository registrationRepository;

    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Override
    public Registration getRegistrationById(long id) {
        return registrationRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveRegistration(Registration registration) {
        registrationRepository.save(registration);
    }

    @Override
    @Transactional
    public void updateRegistration(long id, Registration updetedRegistration) {
        updetedRegistration.setId(id);
        registrationRepository.save(updetedRegistration);
    }

    @Override
    @Transactional
    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }
}

package com.bank.profile.service;

import com.bank.profile.entity.Registration;

import java.util.List;

public interface RegistrationService {
    List<Registration> getAllRegistrations();
    Registration getRegistrationById(long id);
    void saveRegistration(Registration registration);
    void updateRegistration(long id, Registration updetedRegistration);
    void deleteRegistration(long id);
}

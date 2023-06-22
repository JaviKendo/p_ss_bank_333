package com.bank.profile.service;

import com.bank.profile.entity.ActualRegistration;

import java.util.List;

public interface ActualRegistrationService {
    List<ActualRegistration> getAllActualRegistrations();
    ActualRegistration getActualRegistrationById(long id);
    void saveActualRegistration(ActualRegistration actualRegistration);
    void updateActualRegistration(long id, ActualRegistration updetedActualRegistration);
    void deleteActualRegistration(long id);
}

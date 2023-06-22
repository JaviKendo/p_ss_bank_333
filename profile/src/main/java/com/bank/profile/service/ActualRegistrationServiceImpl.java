package com.bank.profile.service;

import com.bank.profile.entity.ActualRegistration;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repository.ActualRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ActualRegistrationServiceImpl implements ActualRegistrationService {

    private final ActualRegistrationRepository actualRegistrationRepository;

    @Autowired
    public ActualRegistrationServiceImpl(ActualRegistrationRepository actualRegistrationRepository) {
        this.actualRegistrationRepository = actualRegistrationRepository;
    }

    @Override
    public List<ActualRegistration> getAllActualRegistrations() {
        return actualRegistrationRepository.findAll();
    }

    @Override
    public ActualRegistration getActualRegistrationById(long id) {
        return actualRegistrationRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    @Transactional
    public void saveActualRegistration(ActualRegistration actualRegistration) {
        actualRegistrationRepository.save(actualRegistration);
    }

    @Override
    @Transactional
    public void updateActualRegistration(long id, ActualRegistration updetedActualRegistration) {
        updetedActualRegistration.setId(id);
        actualRegistrationRepository.save(updetedActualRegistration);
    }

    @Override
    public void deleteActualRegistration(long id) {
        actualRegistrationRepository.deleteById(id);
    }
}

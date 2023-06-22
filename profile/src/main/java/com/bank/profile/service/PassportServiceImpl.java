package com.bank.profile.service;

import com.bank.profile.entity.Passport;
import com.bank.profile.exception.ProfileNotFoundException;
import com.bank.profile.repository.PassportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PassportServiceImpl implements PassportService {

    private final PassportRepository passportRepository;

    @Autowired
    public PassportServiceImpl(PassportRepository passportRepository) {
        this.passportRepository = passportRepository;
    }

    @Override
    public List<Passport> getAllPassports() {
        return passportRepository.findAll();
    }

    @Override
    public Passport getPassportById(long id) {
        return passportRepository.findById(id).orElseThrow(ProfileNotFoundException::new);
    }

    @Override
    @Transactional
    public void savePassport(Passport passport) {
        passportRepository.save(passport);
    }

    @Override
    @Transactional
    public void updatePassport(long id, Passport updetedPassport) {
        updetedPassport.setId(id);
        passportRepository.save(updetedPassport);
    }

    @Override
    @Transactional
    public void deletePassport(long id) {
        passportRepository.deleteById(id);
    }
}

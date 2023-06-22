package com.bank.profile.service;

import com.bank.profile.entity.Passport;

import java.util.List;

public interface PassportService {
    List<Passport> getAllPassports();
    Passport getPassportById(long id);
    void savePassport(Passport passport);
    void updatePassport(long id, Passport updetedPassport);
    void deletePassport(long id);
}

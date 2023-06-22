package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Atm;

import java.util.List;

public interface AtmService {
    List<Atm> getAllAtms();
    Atm getAtmById(Long id);
    void createAtm(Atm atm);
    void updateAtm(Atm atm);
    void deleteAtm(Long id);
}

package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.License;

import java.util.List;

public interface LicenseService {
    List<License> getAllLicense();
    License getLicenseById(Long id);
    void createLicense(License license);
    void updateLicense(License license);
    void deleteLicense(Long id);
}

package com.bank.publicinfo.service;

import com.bank.publicinfo.entity.Certificate;

import java.util.List;

public interface CertificateService {
    List<Certificate> getAllCertificate();
    Certificate getCertificateById(Long id);
    void createCertificate(Certificate certificate);
    void updateCertificate(Certificate certificate);
    void deleteCertificate(Long id);
}

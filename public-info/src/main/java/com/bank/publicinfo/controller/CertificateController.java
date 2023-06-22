package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.CertificateDTO;
import com.bank.publicinfo.entity.Certificate;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.CertificateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/certificate")
@Tag(name="Сертификаты банка", description="Работа с сертификатами банка")
public class CertificateController {
    private final CertificateService certificateService;

    @Autowired
    public CertificateController(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    @GetMapping
    @Operation(summary = "Получение списка всех сертификатов", description = "Позволяет получить список всех сертификатов")
    public List<CertificateDTO> getAllCertificates() {
        log.info("Получен запрос на получение всех Certificates");
        List<Certificate> certificates = certificateService.getAllCertificate();
        log.info("Возвращающий {} Certificates", certificates.size());
        return certificates.stream().map(PublicInfoMapper.INSTANCE::convertToCertificateDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение одного сертификата", description = "Позволяет получить один сертификат")
    public CertificateDTO getCertificateById(@PathVariable("id") Long id) {
        log.info("Получен запрос на получение Certificate с идентификатором {}", id);
        Certificate certificate = certificateService.getCertificateById(id);
        log.info("Возвращающий сертификат с Certificate {}", id);
        return PublicInfoMapper.INSTANCE.convertToCertificateDTO(certificate);
    }

    @PostMapping
    @Operation(summary = "Добавление нового сертификата", description = "Позволяет добавить новый сертификат")
    public Certificate createCertificate(@RequestBody CertificateDTO certificateDTO) {
        Certificate certificate = PublicInfoMapper.INSTANCE.convertToCertificate(certificateDTO);
        log.info("Получен запрос на создание нового Certificate");
        certificateService.createCertificate(certificate);
        return certificate;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление существующего сертификата", description = "Позволяет обновить существующий сертификат")
    public Certificate updateCertificate(@PathVariable("id") Long id, @RequestBody CertificateDTO certificateDTO) {
        Certificate certificate = PublicInfoMapper.INSTANCE.convertToCertificate(certificateDTO);
        certificate.setId(id);
        log.info("Получен запрос на обновление Certificate с идентификатором {}", id);
        certificateService.updateCertificate(certificate);
        return certificate;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление сертификата", description = "Позволяет удалить сертификат")
    public ResponseEntity<HttpStatus> deleteCertificate(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление Certificate с идентификатором {}", id);
        certificateService.deleteCertificate(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

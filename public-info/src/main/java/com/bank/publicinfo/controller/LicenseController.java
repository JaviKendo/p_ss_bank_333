package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.LicenseDTO;
import com.bank.publicinfo.entity.License;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.LicenseService;
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
@RequestMapping("/license")
@Tag(name="Лицензии банка", description="Работа с лизензиями банка")
public class LicenseController {
    private final LicenseService licenseService;

    @Autowired
    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping
    @Operation(summary = "Получение списка всех лицензий", description = "Позволяет получить список всех лицензий")
    public List<LicenseDTO> getAllLicenses() {
        log.info("Получен запрос на получение всех Licenses");
        List<License> licenses = licenseService.getAllLicense();
        log.info("Возвращаем {} Licenses", licenses.size());
        return licenses.stream().map(PublicInfoMapper.INSTANCE::convertToLicenseDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение одной лицензии", description = "Позволяет получить одну лицензию")
    public LicenseDTO getLicenseById(@PathVariable("id") Long id) {
        log.info("Получен запрос на получение License с идентификатором {}", id);
        License license = licenseService.getLicenseById(id);
        log.info("Возвращаем License с идентификатором {}", id);
        return PublicInfoMapper.INSTANCE.convertToLicenseDTO(license);
    }

    @PostMapping
    @Operation(summary = "Добавление новой лицензии", description = "Позволяет добавить новую лицензию")
    public License createLicense(@RequestBody LicenseDTO licenseDTO) {
        License license = PublicInfoMapper.INSTANCE.convertToLicense(licenseDTO);
        log.info("Получен запрос на создание нового License");
        licenseService.createLicense(license);
        return license;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление существующей лицензии", description = "Позволяет обновить существующую лицензию")
    public License updateLicense(@PathVariable("id") Long id, @RequestBody LicenseDTO licenseDTO) {
        License license = PublicInfoMapper.INSTANCE.convertToLicense(licenseDTO);
        license.setId(id);
        log.info("Получен запрос на обновление License с идентификатором {}", id);
        licenseService.updateLicense(license);
        return license;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление лицензии", description = "Позволяет удалить лицензию")
    public ResponseEntity<HttpStatus> deleteLicense(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление License с идентификатором {}", id);
        licenseService.deleteLicense(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

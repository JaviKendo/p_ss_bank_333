package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BankDetailsDTO;
import com.bank.publicinfo.entity.BankDetails;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.BankDetailsService;
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
@RequestMapping("/bank_details")
@Tag(name="Информация о банке", description="Работа с банковскими данными")
public class BankDetailsController {
    private final BankDetailsService bankDetailsService;

    @Autowired
    public BankDetailsController(BankDetailsService bankDetailsService) {
        this.bankDetailsService = bankDetailsService;
    }

    @GetMapping
    @Operation(summary = "Получение списка всех банков", description = "Позволяет получить список всех банков")
    public List<BankDetailsDTO> getAllBankDetails() {
        log.info("Получен запрос на получение всех Bank_details");
        List<BankDetails> bankDetails = bankDetailsService.getAllBankDetails();
        log.info("Возвращающийся {} Bank_details", bankDetails.size());
        return bankDetails.stream().map(PublicInfoMapper.INSTANCE::convertToBankDetailsDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение информации об одном панке", description = "Позволяет получить информацию об одном банке")
    public BankDetailsDTO getCertificateById(@PathVariable("id") Long id) {
        log.info("Получен запрос на получение Bank_details с идентификатором {}", id);
        BankDetails bankDetails = bankDetailsService.getBankDetailsById(id);
        log.info("Возвращает Bank_details с идентификатором {}", id);
        return PublicInfoMapper.INSTANCE.convertToBankDetailsDTO(bankDetails);
    }

    @PostMapping
    @Operation(summary = "Создание записи нового банка", description = "Позволяет создать запись нового банка")
    public BankDetails createBankDetails(@RequestBody BankDetailsDTO bankDetailsDTO) {
        BankDetails bankDetails = PublicInfoMapper.INSTANCE.convertToBankDetails(bankDetailsDTO);
        log.info("Получен запрос на создание нового Bank_details");
        bankDetailsService.createBankDetails(bankDetails);
        return bankDetails;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление информации существующего банка", description = "Позволяет обновить информацию о существующем банке")
    public BankDetails updateBankDetails(@PathVariable("id") Long id, @RequestBody BankDetailsDTO bankDetailsDTO) {
        BankDetails bankDetails = PublicInfoMapper.INSTANCE.convertToBankDetails(bankDetailsDTO);
        bankDetails.setId(id);
        log.info("Получен запрос на обновление Bank_details с идентификатором {}", id);
        bankDetailsService.updateBankDetails(bankDetails);
        return bankDetails;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление записи банка", description = "Позволяет удалить запись банка")
    public ResponseEntity<HttpStatus> deleteBankDetails(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление банковских реквизитов с идентификатором {}", id);
        bankDetailsService.deleteBankDetails(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

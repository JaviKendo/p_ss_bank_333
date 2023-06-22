package com.bank.profile.controller;

import com.bank.profile.dto.PassportDTO;
import com.bank.profile.exception.ProfileNotValidException;
import com.bank.profile.mappers.PassportMapper;
import com.bank.profile.service.PassportService;
import com.bank.profile.util.ErrorsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/passports")
@Tag(name = "Паспорт", description = "Методы для работы с паспортами")
public class PassportController {
    private final PassportService passportService;
    private final PassportMapper passportMapper;

    public PassportController(PassportService passportService, PassportMapper passportMapper) {
        this.passportService = passportService;
        this.passportMapper = passportMapper;
    }

    @GetMapping("/")
    @Operation(summary = "Информация обо всех паспортах")
    public List<PassportDTO> showAllAudits() {
        return passportService.getAllPassports().stream().map(passportMapper::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Информация о паспорте по id")
    public PassportDTO showAuditById(@PathVariable("id") long id) {
        return passportMapper.toDTO(passportService.getPassportById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добаление паспорта")
    public ResponseEntity<HttpStatus> createAudit(@RequestBody PassportDTO passportDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        passportService.savePassport(passportMapper.toEntity(passportDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение информации о паспортами по id")
    public ResponseEntity<HttpStatus> updateAudit(@RequestBody PassportDTO passportDTO,
                                                  @PathVariable("id") long id,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        passportService.updatePassport(id, passportMapper.toEntity(passportDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о паспорте по id")
    public ResponseEntity<HttpStatus> deleteAudit(@PathVariable("id") long id) {
        passportService.deletePassport(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

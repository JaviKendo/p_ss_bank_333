package com.bank.profile.controller;

import com.bank.profile.dto.RegistrationDTO;
import com.bank.profile.exception.ProfileNotValidException;
import com.bank.profile.mappers.RegistrationMapper;
import com.bank.profile.service.RegistrationService;
import com.bank.profile.util.ErrorsUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/registrations")
@Tag(name = "Регистрация", description = "Методы для работы с регистрациями")
public class RegistrationController {
    private final RegistrationService registrationService;
    private final RegistrationMapper registrationMapper;

    @Autowired
    public RegistrationController(RegistrationService registrationService, RegistrationMapper registrationMapper) {
        this.registrationService = registrationService;
        this.registrationMapper = registrationMapper;
    }

    @GetMapping("/")
    @Operation(summary = "Информация обо всех регистрациях")
    public List<RegistrationDTO> showAllProfiles() {
        return registrationService.getAllRegistrations()
                .stream()
                .map(registrationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Информация о регистрации по id")
    public RegistrationDTO showUserById(@PathVariable("id") long id) {
        return registrationMapper.toDTO(registrationService.getRegistrationById(id));
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добаление регистрации")
    public ResponseEntity<HttpStatus> createProfiles(@RequestBody @Valid RegistrationDTO registrationDTO,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        registrationService.saveRegistration(registrationMapper.toEntity(registrationDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение информации о регистрации по id")
    public ResponseEntity<HttpStatus> updateProfiles(@RequestBody @Valid RegistrationDTO registrationDTO,
                                                     @PathVariable("id") long id,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        registrationService.updateRegistration(id, registrationMapper.toEntity(registrationDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о регистрации по id")
    public ResponseEntity<HttpStatus> deleteProfiles(@PathVariable("id") long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

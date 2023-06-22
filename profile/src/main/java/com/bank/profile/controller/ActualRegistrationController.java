package com.bank.profile.controller;

import com.bank.profile.dto.ActualRegistrationDTO;
import com.bank.profile.exception.ProfileNotValidException;
import com.bank.profile.mappers.ActualRegistrationMapper;
import com.bank.profile.service.ActualRegistrationService;
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
@RequestMapping("/actual-registration")
@Tag(name = "Актуальная регистрация", description = "Методы для работы с актуальными регистрациями")
public class ActualRegistrationController {

    private final ActualRegistrationService actualRegistrationService;
    private final ActualRegistrationMapper actualRegistrationMapper;

    public ActualRegistrationController(ActualRegistrationService actualRegistrationService,
                                        ActualRegistrationMapper actualRegistrationMapper) {
        this.actualRegistrationService = actualRegistrationService;
        this.actualRegistrationMapper = actualRegistrationMapper;
    }

    @GetMapping("/")
    @Operation(summary = "Информация обо всех актуальных регистрациях")

    public List<ActualRegistrationDTO> showAllProfiles() {
        return actualRegistrationService.getAllActualRegistrations()
                .stream()
                .map(actualRegistrationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Информация об актуальной регистрации по id")
    public ActualRegistrationDTO showProfileById(@PathVariable("id") long id) {
        return actualRegistrationMapper.toDTO(actualRegistrationService.getActualRegistrationById(id));
    }

    @PostMapping("/")
    @Operation(summary = "Добаление актуальной регистрации")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> createProfiles(@RequestBody ActualRegistrationDTO actualRegistrationDTO,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        actualRegistrationService.saveActualRegistration(actualRegistrationMapper.toEntity(actualRegistrationDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение информации об актуальной регистрации по id")
    public ResponseEntity<HttpStatus> updateProfiles(@RequestBody ActualRegistrationDTO actualRegistrationDTO,
                                                     @PathVariable("id") long id,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        actualRegistrationService
                .updateActualRegistration(id, actualRegistrationMapper.toEntity(actualRegistrationDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации об актуальной регистрации по id")
    public ResponseEntity<HttpStatus> deleteProfiles(@PathVariable("id") long id) {
        actualRegistrationService.deleteActualRegistration(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

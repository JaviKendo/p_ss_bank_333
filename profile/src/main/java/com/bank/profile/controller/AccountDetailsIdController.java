package com.bank.profile.controller;

import com.bank.profile.dto.AccountDetailsIdDTO;
import com.bank.profile.exception.ProfileNotValidException;
import com.bank.profile.mappers.AccountDetailsIdMapper;
import com.bank.profile.service.AccountDetailsIdService;
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
@RequestMapping("/account-details-id")
@Tag(name = "Данные учетной записи", description = "Методы для работы с данными учетной записи")
public class AccountDetailsIdController {
    private final AccountDetailsIdService accountDetailsIdService;
    private final AccountDetailsIdMapper accountDetailsIdMapper;

    public AccountDetailsIdController(AccountDetailsIdService accountDetailsIdService,
                                      AccountDetailsIdMapper accountDetailsIdMapper) {
        this.accountDetailsIdService = accountDetailsIdService;
        this.accountDetailsIdMapper = accountDetailsIdMapper;
    }

    @GetMapping("/")
    @Operation(summary = "Информация обо всех записях")
    public List<AccountDetailsIdDTO> showAllProfiles() {
        return accountDetailsIdService.getAllAccountDetailsId()
                    .stream()
                    .map(accountDetailsIdMapper::toDTO)
                    .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Информация о записи по id")
    public AccountDetailsIdDTO showProfileById(@PathVariable("id") long id) {
        return accountDetailsIdMapper.toDTO(accountDetailsIdService.getAccountDetailsIdById(id));
    }

    @PostMapping("/")
    @Operation(summary = "Добаление записи")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<HttpStatus> createProfiles(@RequestBody AccountDetailsIdDTO accountDetailsIdDTO,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        accountDetailsIdService.saveAccountDetailsId(accountDetailsIdMapper.toEntity(accountDetailsIdDTO));
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение записи по id")
    public ResponseEntity<HttpStatus> updateProfiles(@RequestBody AccountDetailsIdDTO accountDetailsIdDTO,
                                                     @PathVariable("id") long id,
                                                     BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        accountDetailsIdService.updateAccountDetailsId(id, accountDetailsIdMapper.toEntity(accountDetailsIdDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление записи по id")
    public ResponseEntity<HttpStatus> deleteProfiles(@PathVariable("id") long id) {
        accountDetailsIdService.deleteAccountDetailsId(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

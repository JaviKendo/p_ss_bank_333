package com.bank.transfer.controller;

import com.bank.transfer.dto.transferDto.AccountTransferDto;
import com.bank.transfer.exception.TransferRequestException;
import com.bank.transfer.service.accountTransfer.AccountTransferService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/")
@AllArgsConstructor
@Log(topic = "AccountTransferController")
@Tag(name = "CRUD для работы с AccountTransfer'ом")
public class RestAccountTransferController {

    private final AccountTransferService accountTransferService;

    @Operation(
            summary = "Получение всех AccountTransfer'ов",
            description = "Просто находит всех AccountTransfer'ов"
    )
    @Timed("Test get all AccountTransfer")
    @GetMapping("/account-transfers")
    public List<AccountTransferDto> getAccountTransfers() {
        log.info("Принят запрос на получение всех AccountTransfer");
        return accountTransferService.getAllTransfer();
    }

    @Operation(
            summary =  "Получение AccountTransfer'а по id",
            description = "Просто находит AccountTransfer по id"
    )
    @GetMapping("/account-transfers/{id}")
    public AccountTransferDto getAccountTransfer(@Parameter(description = "Для выполнения необходимо передать id")
                                                  @PathVariable Long id) {
        log.info("Принят запрос на получение AccountTransfer с id = " + id);
        return accountTransferService.getTransferById(id);
    }

    @Operation(
            summary =  "Добавление нового AccountTransfer'а",
            description = "Просто добавляет новый AccountTransfer"
    )
    @PostMapping("/account-transfers")
    public void saveAccountTransfer(
            @Parameter(description = """
                    accountNumber должен быть уникален;
                     purpose может быть пустым;
                     все поля кроме purpose должны быть не нулевыми;
                     поле id не заполнять во избежании обновлениясуществующего AccountTransfer'а""")
            @RequestBody @Valid AccountTransferDto accountTransferDto,
            BindingResult bindingResult) {
        log.info("Принят запрос на добавление AccountTransfer");
        if (bindingResult.hasErrors()) {
            throw new TransferRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        accountTransferService.saveTransfer(accountTransferDto);
    }

    @Operation(
            summary = "Обновление существующего AccountTransfer'а",
            description = "Просто обновляет AccountTransfer"
    )
    @PutMapping("/account-transfers")
    public void updateAccountTransfer(
            @Parameter(description = """
                    accountNumber должен быть уникален;
                     purpose может быть пустым;
                     все поля кроме purpose должны быть не нулевыми""")
            @Valid @RequestBody AccountTransferDto accountTransferDto,
            BindingResult bindingResult) {
        log.info("Принят запрос на изменение AccountTransfer");
        if (bindingResult.hasErrors()) {
            throw new TransferRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        accountTransferService.saveTransfer(accountTransferDto);
    }


    @Operation(
            summary = "Удаление AccountTransfer'а по id",
            description = "Просто удаляет AccountTransfer по id"
    )
    @DeleteMapping("/account-transfers/{id}")
    public void deleteAccountTransfer(@Parameter(description = "Для выполнения необходимо передать id")
                                          @PathVariable long id) {
        log.info("Принят запрос на удаление AccountTransfer с id = " + id);
        accountTransferService.deleteTransferById(id);
    }
}

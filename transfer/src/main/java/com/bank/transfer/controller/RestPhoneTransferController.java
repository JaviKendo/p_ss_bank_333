package com.bank.transfer.controller;

import com.bank.transfer.dto.transferDto.PhoneTransferDto;
import com.bank.transfer.exception.TransferRequestException;
import com.bank.transfer.service.phoneTransfer.PhoneTransferService;
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
@Log(topic = "PhoneTransferController")
@Tag(name = "CRUD для работы с PhoneTransfer'ом")
public class RestPhoneTransferController {
    private final PhoneTransferService phoneTransferService;

    @Operation(
            summary = "Получение всех PhoneTransfer'ов",
            description = "Просто находит всех PhoneTransfer'ов"
    )
    @GetMapping("/phone-transfers")
    public List<PhoneTransferDto> getPhoneTransfers() {
        log.info("Принят запрос на получение всех PhoneTransfer");
        return phoneTransferService.getAllTransfer();
    }

    @Operation(
            summary = "Получение PhoneTransfer'а по id",
            description = "Просто находит PhoneTransfer по id"
    )
    @GetMapping("/phone-transfers/{id}")
    public PhoneTransferDto getPhoneTransfer(@Parameter(description = "Для выполнения необходимо передать id")
                                              @PathVariable Long id) {
        log.info("Принят запрос на получение PhoneTransfer с id = " + id);
        return phoneTransferService.getTransferById(id);
    }

    @Operation(
            summary = "Добавление нового PhoneTransfer'а",
            description = "Просто добавляет новый PhoneTransfer"
    )
    @PostMapping("/phone-transfers")
    public void savePhoneTransfer(
            @Parameter(description = """
                    purpose может быть пустым;
                     все поля кроме purpose должны быть не нулевыми;
                     поле id не заполнять во избежании обновления существующего AccountTransfer'а""")
            @RequestBody @Valid PhoneTransferDto phoneTransferDto, BindingResult bindingResult) {
        log.info("Принят запрос на добавление PhoneTransfer");
        if (bindingResult.hasErrors()) {
            throw new TransferRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        phoneTransferService.saveTransfer(phoneTransferDto);
    }

    @Operation(
            summary = "Обновление существующего PhoneTransfer'а",
            description = "Просто обновляет PhoneTransfer"
    )
    @PutMapping("/phone-transfers")
    public void updatePhoneTransfer(
            @Parameter(description = """
                    purpose может быть пустым;
                    все поля кроме purpose должны быть не нулевыми""")
            @RequestBody @Valid PhoneTransferDto phoneTransferDto, BindingResult bindingResult) {
        log.info("Принят запрос на изменение PhoneTransfer");
        if (bindingResult.hasErrors()) {
            throw new TransferRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        phoneTransferService.saveTransfer(phoneTransferDto);
    }

    @Operation(
            summary = "Удаление PhoneTransfer'а по id",
            description = "Просто удаляет PhoneTransfer по id"
    )
    @DeleteMapping("/phone-transfers/{id}")
    public void deletePhoneTransfer(@Parameter(description = "Для выполнения необходимо передать id")
                                        @PathVariable long id) {
        log.info("Принят запрос на удаление PhoneTransfer с id = " + id);
        phoneTransferService.deleteTransferById(id);
    }

}

package com.bank.transfer.controller;

import com.bank.transfer.dto.transferDto.CardTransferDto;
import com.bank.transfer.exception.TransferRequestException;
import com.bank.transfer.service.cardTransfer.CardTransferService;
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
@Log(topic = "CardTransferController")
@Tag(name = "CRUD для работы с CardTransfer'ом")
public class RestCardTransferController {
    private final CardTransferService cardTransferService;

    @Operation(
            summary = "Получение всех CardTransfer'ов",
            description = "Просто находит всех CardTransfer'ов"
    )
    @GetMapping("/card-transfers")
    public List<CardTransferDto> getCardTransfers() {
        log.info("Принят запрос на получение всех CardTransfer");
        return cardTransferService.getAllTransfer();
    }

    @Operation(
            summary = "Получение CardTransfer'а по id",
            description = "Просто находит CardTransfer по id"
    )
    @GetMapping("/card-transfers/{id}")
    public CardTransferDto getCardTransfer(@Parameter(description = "Для выполнения необходимо передать id")
                                            @PathVariable Long id) {
        log.info("Принят запрос на получение CardTransfer с id = " + id);
        return cardTransferService.getTransferById(id);
    }

    @Operation(
            summary = "Добавление нового CardTransfer'а",
            description = "Просто находит добавляет новый CardTransfer"
    )
    @PostMapping("/card-transfers")
    public void saveCardTransfer(
            @Parameter(description = """
                    cardNumber должен быть уникален;
                     purpose может быть пустым;
                     все поля кроме purpose должны быть не нулевыми;
                     поле id не заполнять во избежании обновлениясуществующего AccountTransfer'а""")
            @RequestBody @Valid CardTransferDto cardTransferDto, BindingResult bindingResult) {
        log.info("Принят запрос на добавление CardTransfer");
        if (bindingResult.hasErrors()) {
            throw new TransferRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        cardTransferService.saveTransfer(cardTransferDto);
    }

    @Operation(
            summary = "Обновление существующего CardTransfer'а",
            description = "Просто обновляет CardTransfer"
    )
    @PutMapping("/card-transfers")
    public void updateCardTransfer(
            @Parameter(description = """
                    cardNumber должен быть уникален;
                     purpose может быть пустым;
                     все поля кроме purpose должны быть не нулевыми""")
            @RequestBody @Valid CardTransferDto cardTransferDto, BindingResult bindingResult) {
        log.info("Принят запрос на изменение CardTransfer");
        if (bindingResult.hasErrors()) {
            throw new TransferRequestException(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        cardTransferService.saveTransfer(cardTransferDto);
    }

    @Operation(
            summary = "Удаление CardTransfer'а по id",
            description = "Просто удаляет CardTransfer по id"
    )
    @DeleteMapping("/card-transfers/{id}")
    public void deleteCardTransfer(@Parameter(description = "Для выполнения необходимо передать id")
                                       @PathVariable long id) {
        log.info("Принят запрос на удаление CardTransfer с id = " + id);
        cardTransferService.deleteTransferById(id);
    }
}

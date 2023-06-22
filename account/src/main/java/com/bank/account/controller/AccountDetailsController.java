package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.service.AccountDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Контроллер для управления операциями со счетами банковских деталей.
 * Обрабатывает HTTP-запросы, связанные с операциями создания, получения, обновления и удаления счетов.
 */
@RestController
@RequestMapping("/details")
@RequiredArgsConstructor
@Tag(name = "AccountDetailsController", description = "Этот микросервис отвечает за банковский счета и за все операции, " +
        "которые отвечают за логику банковского счета и за изменение сущностей данного микросервиса")
public class AccountDetailsController {
    private final AccountDetailsService accountService;
    private final Logger logger = LoggerFactory.getLogger(AccountDetailsController.class);
    /**
     * Создает новую запись банковских деталей.
     *
     * @param detailsDTO  DTO с информацией о банковских деталях
     * @return HTTP-статус ответа
     */
    @PostMapping
    @Operation(summary  = "Создание новой записи")
    ResponseEntity<HttpStatus> saveAccountDetails(@RequestBody @Valid AccountDetailsDTO detailsDTO) {
        logger.info("запущен метод saveAccountDetails");
        accountService.save(detailsDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    /**
     * Возвращает запись банковских деталей по заданному идентификатору.
     *
     * @param id  идентификатор записи
     * @return DTO с информацией о банковских деталях
     */

    @GetMapping("/{id}")
    @Operation(summary  = "Получение записи по id")
    ResponseEntity<AccountDetailsDTO> getAccountDetails(@PathVariable Long id) {
        logger.info("запущен метод getAccountDetails");
        return new ResponseEntity<>(accountService.getAccountDetails(id), HttpStatus.OK);
    }
    /**
     * Возвращает все записи банковских деталей.
     *
     * @return список DTO с информацией о банковских деталях
     */
    @GetMapping
    @Operation(summary  = "Получение всех записей")
    ResponseEntity<List<AccountDetailsDTO>> getAllAccountDetails() {
        logger.info("запущен метод getAllAccountDetails");
        return new ResponseEntity<>(accountService.getAllAccountDetails(), HttpStatus.OK);
    }
    /**
     * Удаляет запись банковских деталей по заданному идентификатору.
     *
     * @param id  идентификатор записи
     * @return HTTP-статус ответа
     */
    @DeleteMapping("/{id}")
    @Operation(summary  = "Удаление записи")
    ResponseEntity<HttpStatus> deleteAccountDetails(@PathVariable Long id) {
        logger.info("запущен метод deleteAccountDetails");
        accountService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    /**
     * Обновляет существующую запись банковских деталей.
     *
     * @param detailsDTO  DTO с информацией о банковских деталях
     * @return HTTP-статус ответа
     */
    @PatchMapping
    @Operation(summary  = "Обновление существующей записи")
    ResponseEntity<HttpStatus> updateAccountDetails(@RequestBody @Valid AccountDetailsDTO detailsDTO) {
        logger.info("запущен метод updateAccountDetails");
        accountService.updateAccountDetails(detailsDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
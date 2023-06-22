package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.AtmDTO;
import com.bank.publicinfo.entity.Atm;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.AtmService;
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
@RequestMapping("/atm")
@Tag(name="Банкоматы", description="Работа с банкоматами")
public class AtmController {
    private final AtmService atmService;

    @Autowired
    public AtmController(AtmService atmService) {
        this.atmService = atmService;
    }

    @GetMapping
    @Operation(summary = "Получение списка всех банкоматов", description = "Позволяет получить список всех банкоматов")
    public List<AtmDTO> getAllAtms() {
        log.info("Получен запрос на получение всех Atms");
        List<Atm> atms = atmService.getAllAtms();
        log.info("Возвращающие {} Atms", atms.size());
        return atms.stream().map(PublicInfoMapper.INSTANCE::convertToAtmDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение одного банкомата", description = "Позволяет получить информацию об одном банкомате")
    public AtmDTO getAtmById(@PathVariable("id") Long id) {
        log.info("Получен запрос на получение Atm с идентификатором {}", id);
        Atm atm = atmService.getAtmById(id);
        log.info("Возвращающий Atm с идентификатором {}", id);
        return PublicInfoMapper.INSTANCE.convertToAtmDTO(atm);
    }

    @PostMapping
    @Operation(summary = "Создание новой записи", description = "Позволяет добавить новую запись банкомата")
    public Atm createAtm(@RequestBody AtmDTO atmDTO) {
        Atm atm = PublicInfoMapper.INSTANCE.convertToAtm(atmDTO);
        log.info("Получен запрос на создание нового Atm");
        atmService.createAtm(atm);
        return atm;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление существующей записи", description = "Позволяет обновить существующую запись банкомата")
    public Atm updateAtm(@PathVariable("id") Long id, @RequestBody AtmDTO atmDTO) {
        Atm atm = PublicInfoMapper.INSTANCE.convertToAtm(atmDTO);
        atm.setId(id);
        log.info("Получен запрос на обновление Atm с идентификатором {}", id);
        atmService.updateAtm(atm);
        return atm;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление записи", description = "Позволяет удалить существующую запись банкомата")
    public ResponseEntity<HttpStatus> deleteAtm(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление Atm с идентификатором {}", id);
        atmService.deleteAtm(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

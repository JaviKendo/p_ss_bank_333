package com.bank.publicinfo.controller;

import com.bank.publicinfo.dto.BranchDTO;
import com.bank.publicinfo.entity.Branch;
import com.bank.publicinfo.mapper.PublicInfoMapper;
import com.bank.publicinfo.service.BranchService;
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
@RequestMapping("/branch")
@Tag(name="Отделения банка", description="Работа с отделениями банка")
public class BranchController {
    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @GetMapping
    @Operation(summary = "Получение списка всех отделений банка", description = "Позволяет получить список всех отделений банка")
    public List<BranchDTO> getAllBranchs() {
        log.info("Получен запрос на получение всех Branchs");
        List<Branch> branches = branchService.getAllBranch();
        log.info("Возвращающие {} Branchs", branches.size());
        return branches.stream().map(PublicInfoMapper.INSTANCE::convertToBranchDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение одного отделения банка", description = "Позволяет получить информацию об одном отделении банка")
    public BranchDTO getBranchById(@PathVariable("id") Long id) {
        log.info("Получен запрос на получение Branch с идентификатором {}", id);
        Branch branch = branchService.getBranchById(id);
        log.info("Возвращающая Branch с идентификатором {}.", id);
        return PublicInfoMapper.INSTANCE.convertToBranchDTO(branch);
    }

    @PostMapping
    @Operation(summary = "Добавление нового отделения банка", description = "Позволяет добавить новое отдедение банка")
    public Branch createBranch(@RequestBody BranchDTO branchDTO) {
        Branch branch = PublicInfoMapper.INSTANCE.convertToBranch(branchDTO);
        log.info("Получен запрос на создание нового Branch");
        branchService.createBranch(branch);
        return branch;
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление существующего отделения банка", description = "Позволяет обновить существующее отделение банка")
    public Branch updateBranch(@PathVariable("id") Long id, @RequestBody BranchDTO branchDTO) {
        Branch branch = PublicInfoMapper.INSTANCE.convertToBranch(branchDTO);
        branch.setId(id);
        log.info("Получен запрос на обновление Branch с идентификатором {}", id);
        branchService.updateBranch(branch);
        return branch;
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление отделения банка", description = "Позволяет удалить отделение банка")
    public ResponseEntity<HttpStatus> deleteBranch(@PathVariable("id") Long id) {
        log.info("Получен запрос на удаление Branch с идентификатором {}", id);
        branchService.deleteBranch(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

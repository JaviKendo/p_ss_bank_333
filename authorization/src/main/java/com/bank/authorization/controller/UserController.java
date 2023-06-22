package com.bank.authorization.controller;

import com.bank.authorization.dto.UserDTO;
import com.bank.authorization.service.UserService;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Пользователи", description = "Операции, связанные с пользователями")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("Создание нового пользователя")
    @PostMapping
    public UserDTO createUser(@Parameter(description = "DTO с данными пользователя") @RequestBody UserDTO userDTO) {
        return userService.save(userDTO);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Обновление данных пользователя")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        final UserDTO updatedUserDTO = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }
    @Operation(summary = "Удаление пользователя")
    @DeleteMapping("/{id}")
    public void deleteUser(@Parameter(description = "Идентификатор пользователя") @PathVariable Long id) {
        userService.delete(id);
    }

    @Operation(summary = "Получение списка всех пользователей")
    @Timed(value = "findById.time", description = "Time taken to get authorization details by id")
    @Counted(value = "findById.count", description = "Number of times getById method has been invoked")
    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @Operation(summary = "Получение пользователя по идентификатору")
    @GetMapping("/{id}")
    public UserDTO getUserById(@Parameter(description = "Идентификатор пользователя") @PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Получение пользователя по идентификатору профиля")
    @GetMapping("/profile/{profileId}")
    public UserDTO getUserByProfileId(@Parameter(description = "Идентификатор профиля") @PathVariable Long profileId) {
        return userService.findByProfileId(profileId);
    }
}

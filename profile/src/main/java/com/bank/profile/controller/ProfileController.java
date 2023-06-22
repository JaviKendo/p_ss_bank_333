package com.bank.profile.controller;

import com.bank.profile.dto.ProfileDTO;
import com.bank.profile.entity.Profile;
import com.bank.profile.exception.ProfileNotValidException;
import com.bank.profile.mappers.ProfileMapper;
import com.bank.profile.service.ProfileService;
import com.bank.profile.util.ErrorsUtil;
import com.bank.profile.validator.ProfileValidator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
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

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/profiles")
@Tag(name = "Профиль", description = "Методы для работы с профилями")
public class ProfileController {

    private final ProfileService profileService;
    private final ProfileMapper profileMapper;
    private final ProfileValidator profileValidator;


    @Autowired
    public ProfileController(ProfileService profileService,
                             ProfileMapper profileMapper,
                             ProfileValidator profileValidator) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
        this.profileValidator = profileValidator;
    }

    @GetMapping("/")
    @Operation(summary = "Информация обо всех профилях")
    public List<ProfileDTO> showAllProfiles() {
        log.info("Получен запрос на получение всех профилей");
        List<ProfileDTO> allProfileDTO = profileService.getAllProfiles().stream()
                                                .map(profileMapper::toDTO)
                                                .collect(Collectors.toList());
        log.info("Возвращено {} записей", allProfileDTO.size());
        return allProfileDTO;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Информация о профиле по id")
    public ProfileDTO showProfileById(@PathVariable("id") long id) {
        log.info("Получен запрос на получение профиля с id = {}", id);
        ProfileDTO profileDTO = profileMapper.toDTO(profileService.getProfileById(id));
        log.info("Профиль с id = {} возращён", id);
        return profileDTO;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Добаление профиля")
    public ResponseEntity<HttpStatus> createProfiles(@RequestBody ProfileDTO profileDTO,
                                                     BindingResult bindingResult) {
        log.info("Получен запрос на добаление профиля");

        Profile profile = profileMapper.toEntity(profileDTO);

        profileValidator.validate(profile, bindingResult);

        if (bindingResult.hasErrors()) {
            log.warn("Добавляемый профиль не прошел валидацию");
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        profileService.saveProfile(profile);
        log.info("Профиль добавлен");
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Изменение информации о профиле по id")
    public ResponseEntity<HttpStatus> updateProfiles(@RequestBody ProfileDTO profileDTO,
                                                     @PathVariable("id") long id,
                                                     BindingResult bindingResult) {
        log.info("Получен запрос на изменение профиля с id = {}", id);

        Profile profile = profileMapper.toEntity(profileDTO);

        profileValidator.validate(profile, bindingResult);

        if (bindingResult.hasErrors()) {
            log.warn("Изменяемый профиль не прошел валидацию");
            throw new ProfileNotValidException(ErrorsUtil.returnErrorsMsg(bindingResult));
        }
        profileService.updateProfile(id, profileMapper.toEntity(profileDTO));
        log.info("Профиль изменён");
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление информации о профиле по id")
    public ResponseEntity<HttpStatus> deleteProfiles(@PathVariable("id") long id) {
        log.info("Получен запрос на удаление профиля с id = {}", id);
        profileService.deleteProfile(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}

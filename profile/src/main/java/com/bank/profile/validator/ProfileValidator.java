package com.bank.profile.validator;

import com.bank.profile.entity.Profile;
import com.bank.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProfileValidator implements Validator {

    private final ProfileService profileService;

    @Autowired
    public ProfileValidator(ProfileService profileService) {
        this.profileService = profileService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Profile.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Profile profile = (Profile) target;

        if (profileService.findByInn(profile.getInn()).isPresent()) {
            errors.rejectValue("inn", "Профиль с таким ИНН уже существует");
        }

        if (profileService.findBySnils(profile.getSnils()).isPresent()) {
            errors.rejectValue("snils", "Профиль с таким СНИЛСом уже существует");
        }
    }
}

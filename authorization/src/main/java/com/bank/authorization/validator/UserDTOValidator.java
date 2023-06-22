package com.bank.authorization.validator;

import com.bank.authorization.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDTOValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        UserDTO userDTO = (UserDTO) target;
    }
}

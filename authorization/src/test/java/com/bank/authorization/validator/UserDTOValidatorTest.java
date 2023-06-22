package com.bank.authorization.validator;

import com.bank.authorization.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDTOValidatorTest {

    private UserDTOValidator userDTOValidator;
    private UserDTO userDTO;
    private Errors errors;
    @BeforeEach
    public void setUp() {
        userDTOValidator = new UserDTOValidator();
        userDTO = new UserDTO();
    }
    @Test
    public void testSupports() {
        assertTrue(userDTOValidator.supports(UserDTO.class));
        assertFalse(userDTOValidator.supports(Object.class));
    }
    @Test
    public void testValidateNoErrors() {
        userDTO.setId(1L);
        userDTO.setRole("Admin");
        userDTO.setProfileId(2L);
        userDTO.setPassword("password123");
        errors = new BeanPropertyBindingResult(userDTO, "userDTO");
        userDTOValidator.validate(userDTO, errors);
        assertFalse(errors.hasErrors());
    }
}

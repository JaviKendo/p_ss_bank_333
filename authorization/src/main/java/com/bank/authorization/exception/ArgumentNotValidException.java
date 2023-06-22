package com.bank.authorization.exception;

import lombok.Getter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.BindingResult;

@Getter
public class ArgumentNotValidException extends DataIntegrityViolationException {

    private final BindingResult bindingResult;

    public ArgumentNotValidException(BindingResult bindingResult) {
        super("data is incorrect");
        this.bindingResult = bindingResult;
    }
}

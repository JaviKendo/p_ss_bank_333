package com.bank.profile.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static String returnErrorsMsg(BindingResult bindingResult) {
        StringBuilder errorMsg =  new StringBuilder();

        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            errorMsg.append(error.getField())
                    .append(" — ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }
        return errorMsg.toString();
    }
}

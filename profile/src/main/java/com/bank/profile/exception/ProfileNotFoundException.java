package com.bank.profile.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(String message) {
        super(message);
    }
}

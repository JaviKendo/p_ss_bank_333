package com.bank.history.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class HistoryErrorResponse {
    public Long validate(Long id) throws HistoryNotFoundException {
        if (id == -1) {
            throw new HistoryNotFoundException("Invalid id value");
        }
        return id;
    }
}

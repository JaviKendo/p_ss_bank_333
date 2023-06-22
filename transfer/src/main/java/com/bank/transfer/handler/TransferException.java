package com.bank.transfer.handler;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public record TransferException(String massage, HttpStatus httpStatus, ZonedDateTime zonedDateTime) {
}

package com.bank.transfer.exception;

public class TransferRequestException extends RuntimeException {
    public TransferRequestException(String message) {
        super(message);
    }
}

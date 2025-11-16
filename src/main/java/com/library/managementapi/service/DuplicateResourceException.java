package com.library.managementapi.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Apabila Exception ini dicampak, Spring akan automatik membalas dengan status 409 CONFLICT
@ResponseStatus(HttpStatus.CONFLICT) 
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException(String message) {
        super(message);
    }
}
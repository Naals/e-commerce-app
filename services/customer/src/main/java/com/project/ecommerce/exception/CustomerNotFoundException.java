package com.project.ecommerce.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerNotFoundException extends RuntimeException {

    private final String msg;

    public CustomerNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }

}
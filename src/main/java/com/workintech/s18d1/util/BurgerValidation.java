package com.workintech.s18d1.util;

import com.workintech.s18d1.exceptions.BurgerException;
import org.springframework.http.HttpStatus;

public class BurgerValidation {
    public static void checkId(Long id){
        if(id <= 0){
            throw new BurgerException("Id cannot be zero or negative: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void checkBurger(Object burger, String message) {
        if (burger == null) {
            throw new BurgerException(message, HttpStatus.NOT_FOUND);
        }
    }
}
